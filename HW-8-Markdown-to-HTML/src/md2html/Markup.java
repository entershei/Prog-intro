package md2html;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Markup implements TextObj {
    final List<TextObj> children;
    String htmlBegin = "", htmlEnd = "";

    //markdownBegin = "", markdownEnd = "";

    Markup() {
        children = new ArrayList<>();
    }

    void push(TextObj s) {
        children.add(s);
    }

    private void toHtmlBegin(StringBuilder str) {
        str.append(htmlBegin);
    }

    private void toHtmlEnd(StringBuilder str) {
        str.append(htmlEnd);
    }

    @Override
    public void toHtml(StringBuilder str) {
        toHtmlBegin(str);
        //System.out.println(str);

        for (TextObj to : children) {
            try {
                to.toHtml(str);
            } catch (NullPointerException e) {
                System.err.println(str.toString());
            }

        }

        toHtmlEnd(str);
        //System.out.println(str);
    }
}

