package markup;

import java.util.List;

public class Markup implements TextObj {
    List<TextObj> children;
    String markdownBegin = "", markdownEnd = "", htmlBegin = "", htmlEnd = "";

    Markup(List<TextObj> children_) {
        children = children_;
    }

    private void toMarkdownBegin(StringBuilder str) {
        str.append(markdownBegin);
    }

    private void toMarkdownEnd(StringBuilder str) {
        str.append(markdownEnd);
    }

    private void toHtmlBegin(StringBuilder str) {
        str.append(htmlBegin);
    }

    private void toHtmlEnd(StringBuilder str) {
        str.append(htmlEnd);
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        toMarkdownBegin(str);

        for (TextObj to : children) {
            to.toMarkdown(str);
        }

        toMarkdownEnd(str);
    }

    @Override
    public void toHtml(StringBuilder str) {
        toHtmlBegin(str);
        System.out.println(str);
        for (TextObj to : children) {
            to.toHtml(str);
        }

        toHtmlEnd(str);
        System.out.println(str);
    }
}
