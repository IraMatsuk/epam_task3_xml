package by.matsukiryna.xmltask.parser;

import by.matsukiryna.xmltask.entity.ParserType;

public class PaperBuilderFactory {
    private PaperBuilderFactory() {

    }

    public static AbstractPaperBuilder createPaperBuilder(String typeParser) {
        ParserType type = ParserType.valueOf(typeParser.toUpperCase());

        switch (type) {
            case SAX:
                return new SAXPaperParser();
            case DOM:
                return new DOMPaperParser();
            case STAX:
                return new StAXPaperParser();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
