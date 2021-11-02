package by.matsukiryna.xmltask.parser;

import by.matsukiryna.xmltask.entity.ParserType;

public class PaperBuilderFactory {
    private PaperBuilderFactory() {

    }

    public static AbstractPaperBuilder createPaperBuilder(String typeParser) {
        ParserType type = ParserType.valueOf(typeParser.toUpperCase());

        switch (type) {
            case SAX:
                return new PapersSaxBuilder();
            case DOM:
                return new PapersDomBuilder();
            case STAX:
                return new PapersStaxBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
