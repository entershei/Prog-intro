package markup;

public class Text implements TextObj {
    String s;

    Text(String s_) {
        s = s_;
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(s);
    }

    @Override
    public void toHtml(StringBuilder str) {
        str.append(s);
    }
}
