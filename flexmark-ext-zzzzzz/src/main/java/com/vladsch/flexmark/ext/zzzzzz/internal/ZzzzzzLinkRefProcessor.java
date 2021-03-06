package com.vladsch.flexmark.ext.zzzzzz.internal;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.ext.zzzzzz.Zzzzzz;
import com.vladsch.flexmark.parser.LinkRefProcessor;
import com.vladsch.flexmark.parser.LinkRefProcessorFactory;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

public class ZzzzzzLinkRefProcessor implements LinkRefProcessor {
    static final boolean WANT_EXCLAMATION_PREFIX = false;
    static final int BRACKET_NESTING_LEVEL = 1;

    private final ZzzzzzOptions options;

    public ZzzzzzLinkRefProcessor(Document document) {
        this.options = new ZzzzzzOptions(document);
    }

    @Override
    public boolean getWantExclamationPrefix() {
        return WANT_EXCLAMATION_PREFIX;
    }

    @Override
    public int getBracketNestingLevel() {
        return BRACKET_NESTING_LEVEL;
    }

    @Override
    public boolean isMatch(BasedSequence nodeChars) {
        return nodeChars.length() >= 4 && nodeChars.charAt(0) == '[' && nodeChars.charAt(1) == '[' && nodeChars.endCharAt(1) == ']' && nodeChars.endCharAt(2) == ']';
    }

    @Override
    public BasedSequence adjustInlineText(Document document, Node node) {
        // nothing to do, our prefixes are stripped out of a link ref
        return node.getChars();
    }

    @Override
    public boolean allowDelimiters(final BasedSequence chars, final Document document, final Node node) {
        return true;
    }

    @Override
    public void updateNodeElements(final Document document, final Node node) {

    }

    @Override
    public Node createNode(BasedSequence nodeChars) {
        return new Zzzzzz(nodeChars, options.zzzzzzOption2);
    }

    public static class Factory implements LinkRefProcessorFactory {
        @Override
        public LinkRefProcessor create(Document document) {
            return new ZzzzzzLinkRefProcessor(document);
        }

        @Override
        public boolean getWantExclamationPrefix(DataHolder options) {
            return WANT_EXCLAMATION_PREFIX;
        }

        @Override
        public int getBracketNestingLevel(DataHolder options) {
            return BRACKET_NESTING_LEVEL;
        }
    }
}
