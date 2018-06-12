package markup;

import java.util.List;

public class Strong extends Markup {
    Strong(List<TextObj> children_) {
        super(children_);
        markdownBegin = "__";
        markdownEnd = "__";
        htmlBegin = "<strong>";
        htmlEnd = "</strong>";
    }
}
