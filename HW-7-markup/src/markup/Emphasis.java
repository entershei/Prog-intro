package markup;

import java.util.List;

public class Emphasis extends Markup {
    Emphasis(List<TextObj> children_) {
        super(children_);
        markdownBegin = "*";
        markdownEnd = "*";
        htmlBegin = "<em>";
        htmlEnd = "</em>";
    }
}
