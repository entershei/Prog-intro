package markup;

public interface TextObj {
    void toMarkdown(StringBuilder str);

    void toHtml(StringBuilder str);
}
