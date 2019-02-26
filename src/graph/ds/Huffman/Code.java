package graph.ds.Huffman;

import java.util.Map;

// code contain a string code and a symbol table
public class Code {
    private char[] raw;
    private TreeNode symbolTable;
    private String after;
    public Code(char[] raw, TreeNode symbolTable) {
        this.raw = raw;
        this.symbolTable = symbolTable;
        this.after = HuffmanCode.encode(raw, symbolTable);
    }

    public char[] getRaw() {
        return raw;
    }

    public TreeNode getSymbolTable() {
        return symbolTable;
    }


    public String getAfter() {
        return after;
    }
}
