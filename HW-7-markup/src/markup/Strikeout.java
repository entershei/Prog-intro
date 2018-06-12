package markup;

import java.util.List;

public class Strikeout extends Markup {
    Strikeout(List<TextObj> children_) {
        super(children_);
        markdownBegin = "~";
        markdownEnd = "~";
        htmlBegin = "<s>";
        htmlEnd = "</s>";
    }
}
