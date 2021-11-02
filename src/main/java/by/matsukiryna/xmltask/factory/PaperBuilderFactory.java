package by.matsukiryna.xmltask.factory;

import by.matsukiryna.xmltask.builder.AbstractPaperBuilder;
import by.matsukiryna.xmltask.builder.PapersDomBuilder;
import by.matsukiryna.xmltask.builder.PapersSaxBuilder;
import by.matsukiryna.xmltask.builder.PapersStaxBuilder;
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
