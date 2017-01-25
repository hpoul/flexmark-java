package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.util.options.DataHolder;

/**
 * The context for node rendering, including configuration and functionality for the node renderer to use.
 */
public interface NodeFormatterContext {
    /**
     * @return the HTML writer to use
     */
    MarkdownWriter getMarkdown();

    /**
     * Creates a child rendering context that can be used to collect rendered html text. The child context inherits
     * everything but the HtmlRenderer and doNotRenderLinksNesting from the parent.
     *
     * @param out           appendable to use for generated html
     * @param inheritIndent whether the html writer of the sub-context should inherit the current context's indentation level or start with 0 indentation
     * @return a new rendering context with a given appendable for its output
     */
    NodeFormatterContext getSubContext(Appendable out, boolean inheritIndent);

    /**
     * Render the specified node and its children using the configured renderers. This should be used to render child
     * nodes; be careful not to pass the node that is being rendered, that would result in an endless loop.
     *
     * @param node the node to render
     */
    void render(Node node);

    /**
     * Render the children of the node, used by custom renderers
     *
     * @param parent node the children of which are to be rendered
     */
    void renderChildren(Node parent);

    /**
     * @return current rendering phase
     */
    FormattingPhase getFormattingPhase();

    /**
     * Get the current rendering context {@link DataHolder}. These are the options passed or set on the {@link HtmlRenderer#builder()} or passed to {@link HtmlRenderer#builder(DataHolder)}.
     * To get the document options you should use {@link #getDocument()} as the data holder.
     *
     * @return the current renderer options {@link DataHolder}
     */
    DataHolder getOptions();

    /**
     * @return the {@link FormatterOptions} for the context.
     */
    FormatterOptions getFormatterOptions();

    /**
     * @return the {@link Document} node of the current context
     */
    Document getDocument();

    /**
     * @return the current node being rendered
     */
    Node getCurrentNode();
}