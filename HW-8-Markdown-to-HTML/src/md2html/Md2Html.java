package md2html;

import java.io.*;
import java.lang.String;
import java.util.*;

import static java.lang.Character.isWhitespace;

public class Md2Html {
    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File(args[0]), "utf8");
                PrintWriter out = new PrintWriter(args[1], "utf8")
        ) {
            in.useDelimiter("\0");

            Deque<Markup> st = new ArrayDeque<Markup>();
            st.add(new Markup());

            String text = in.next().trim();

            boolean hasTwoOrMoreNewlines = true;
            boolean titleIsOpened = false;

            for (int i = 0; i < text.length(); i++) {
                if (i < text.length() - 1) {
                    if (((text.charAt(i) == '*' && text.charAt(i + 1) == '*') ||
                            (text.charAt(i) == '_' && text.charAt(i + 1) == '_')) && (st.peekLast() instanceof Strong)) {
                        Markup a = st.peekLast();
                        st.pollLast();
                        st.peekLast().push(a);

                        i++;
                        hasTwoOrMoreNewlines = false;
                        titleIsOpened = false;
                        continue;
                    }

                    if ((text.charAt(i) == '*' && text.charAt(i + 1) == '*') ||
                            (text.charAt(i) == '_' && text.charAt(i + 1) == '_')) {
                        st.addLast(new Strong());
                        i++;
                        hasTwoOrMoreNewlines = false;
                        titleIsOpened = false;
                        continue;
                    }

                    if (text.charAt(i) == '-' && text.charAt(i + 1) == '-' && st.peekLast() instanceof Strong) {
                        Markup a = st.peekLast();
                        st.pollLast();
                        st.peekLast().push(a);

                        i++;
                        hasTwoOrMoreNewlines = false;
                        titleIsOpened = false;
                        continue;
                    }

                    if (text.charAt(i) == '-' && text.charAt(i + 1) == '-') {
                        st.addLast(new Strikeout());
                        i++;
                        hasTwoOrMoreNewlines = false;
                        titleIsOpened = false;
                        continue;
                    }

                    if (text.charAt(i) == '+' && text.charAt(i + 1) == '+') {
                        st.addLast(new Underline());
                        i++;
                        hasTwoOrMoreNewlines = false;
                        titleIsOpened = false;
                        continue;
                    }
                }

                if (text.charAt(i) == '\n' && titleIsOpened && i < text.length() && text.charAt(i + 1) != '\n') {
                    hasTwoOrMoreNewlines = true;
                    continue;
                }

                if (text.charAt(i) == '\n') {
                    int c = i + 1;

                    while (c < text.length() && isWhitespace(text.charAt(c))) {
                        if (text.charAt(c) == '\n') {
                            break;
                        }

                        c++;
                    }

                    if (c < text.length() && text.charAt(c) == '\n') {
                        hasTwoOrMoreNewlines = true;

                        if (titleIsOpened) {
                            Markup a = st.peekLast();
                            st.pollLast();
                            st.peekLast().push(a);

                            titleIsOpened = false;
                        }
                        i = c;
                        continue;
                    }
                }

                if ((text.charAt(i) == '*' || text.charAt(i) == '_') && st.peekLast() instanceof Emphasis) {
                    Markup a = st.peekLast();
                    st.pollLast();
                    st.peekLast().push(a);

                    hasTwoOrMoreNewlines = false;
                    titleIsOpened = false;
                    continue;
                }

                if (text.charAt(i) == '\'' &&  st.peekLast() instanceof Code) {
                    Markup a = st.peekLast();
                    st.pollLast();
                    st.peekLast().push(a);

                    hasTwoOrMoreNewlines = false;
                    titleIsOpened = false;
                    continue;
                }

                if (text.charAt(i) == '*' || text.charAt(i) == '_') {
                    st.addLast(new Emphasis());
                    hasTwoOrMoreNewlines = false;
                    i++;
                    titleIsOpened = false;
                    continue;
                }

                if (text.charAt(i) == '\'') {
                    st.addLast(new Code());
                    hasTwoOrMoreNewlines = false;
                    titleIsOpened = false;
                    continue;
                }

                if (hasTwoOrMoreNewlines && text.charAt(i) == '#') {
                    int cnt = 1;
                    ++i;

                    while (i < text.length() && text.charAt(i) == '#') {
                        i++;
                        cnt++;
                    }

                    if (st.size() > 1) {
                        Markup a = st.peekLast();
                        st.pollLast();
                        st.peekLast().push(a);
                    }

                    if (isWhitespace(text.charAt(i))) {
                        st.addLast(new Title(cnt));
                        titleIsOpened = true;
                        hasTwoOrMoreNewlines = false;
                    } else {
                        st.addLast(new Paragraph());
                        st.peekLast().push(new Text(text.substring(i - cnt, i)));
                    }
                } else if (hasTwoOrMoreNewlines) {
                    if (st.size() > 1) {
                        Markup a = st.pollLast();
                        st.peekLast().push(a);
                    }
                    st.addLast(new Paragraph());
                    titleIsOpened = false;
                    hasTwoOrMoreNewlines = false;
                    st.peekLast().push(new Text(text.charAt(i) + ""));
                } else {
                    //System.err.println("add " + text.charAt(i));
                    st.peekLast().push(new Text(text.charAt(i) + ""));
                    titleIsOpened = false;
                    hasTwoOrMoreNewlines = false;
                }
            }

            while (st.size() > 1) {
                Markup a = st.pollLast();
                st.peekLast().push(new Text(a));
            }

            StringBuilder ans = new StringBuilder();
            st.peekLast().toHtml(ans);
            out.print(ans);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
