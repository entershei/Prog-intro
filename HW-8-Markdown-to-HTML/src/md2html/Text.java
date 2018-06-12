package md2html;

public class Text implements TextObj {
    String s;

    Text(String s_) {
        s = s_;
    }

    Text(Markup s) {
        s.htmlBegin = "";
        s.htmlEnd = "";
    }

    @Override
    public void toHtml(StringBuilder str) {
        for (char c : s.toCharArray()) {
            if (c == '<') {
                str.append("&lt;");
            } else if (c == '>') {
                str.append("&gt;");
            } else if (c == '&') {
                str.append("&amp;");
            } else {
                str.append(c);
            }
        }
    }
}
