package com.corentingambier.treenode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NaryTreeNodeTest {
    @Test
    public void getValue() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("value");
        assertEquals("value", treeNode.getValue());
    }

    @Test
    public void setValue() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        assertNull(treeNode.getValue());
        treeNode.setValue("value");
        assertEquals("value", treeNode.getValue());
    }

    @Test
    public void getChild() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        assertEquals(child, treeNode.getChild(0));
    }

    @Test
    public void getChildren() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final List<NaryTreeNode<String>> children = new LinkedList<>();
        for (int i = 0; i < nbChildren; i++) {
            children.add(new NaryTreeNode<>());
        }
        for (final NaryTreeNode<String> child : children) {
            treeNode.addChild(child);
        }
        final List<NaryTreeNode<String>> childrenReturned = treeNode.getChildren();
        for (int i = 0; i < nbChildren; i++) {
            assertEquals(children.get(i), childrenReturned.get(i));
        }
    }

    @Test
    public void getChildrenIsNotModifiable() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final List<NaryTreeNode<String>> children = treeNode.getChildren();
        try {
            children.add(new NaryTreeNode<>());
            fail("Should have thrown an UnsupportedOperationException exception");
        } catch (final UnsupportedOperationException e) {
            // OK
        }
    }

    @Test
    public void addChild() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        assertEquals(child, treeNode.getChild(0));
    }

    @Test
    public void removeChild() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        assertEquals(1, treeNode.getChildrenCount());
        treeNode.removeChild(0);
        assertEquals(0, treeNode.getChildrenCount());
        treeNode.addChild(child);
        treeNode.removeChild(child);
        assertEquals(0, treeNode.getChildrenCount());
    }

    @Test
    public void getChildrenCount() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        for (int i = 0; i < nbChildren; i++) {
            treeNode.addChild(new NaryTreeNode<>());
        }
        assertEquals(nbChildren, treeNode.getChildrenCount());
    }

    @Test
    public void generateText() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child1 = new NaryTreeNode<>("child 1");
        treeNode.addChild(child1);
        for (int i = 0; i < nbChildren; i++) {
            child1.addChild(new NaryTreeNode<>(String.valueOf(i)));
        }
        final NaryTreeNode<String> child2 = new NaryTreeNode<>("child 1");
        child1.addChild(child2);
        for (int i = 0; i < nbChildren; i++) {
            child2.addChild(new NaryTreeNode<>(String.valueOf(i)));
        }
        assertEquals(
                "[root] ([child 1] ([0], [1], [2], [3], [4], [5], [6], [7], [8], [9], [child 1] ([0], [1], [2], [3], [4], [5], [6], [7], [8], [9])))",
                treeNode.generateText());
    }

    @Test
    public void isLeaf() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        assertTrue(treeNode.isLeaf());
        treeNode.addChild(new NaryTreeNode<>());
        assertFalse(treeNode.isLeaf());
    }

    @Test
    public void contains() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertTrue(treeNode.contains("root"));
        assertTrue(treeNode.contains("child"));
        assertFalse(treeNode.contains("not found"));
    }

    @Test
    public void getHeight() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(2, treeNode.getHeight());
    }

    @Test
    public void getSize() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(2, treeNode.getSize());
    }

    @Test
    public void getNumberOfLeaves() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(1, treeNode.getNumberOfLeaves());
    }

    @Test
    public void getNumberOfNodes() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(2, treeNode.getNumberOfNodes());
    }

    @Test
    public void toJson() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        final NaryTreeNode<String> subChild = new NaryTreeNode<>("subChild");
        assertEquals("{\"value\":\"root\"}", treeNode.toJson());
        treeNode.addChild(child);
        assertEquals("{\"value\":\"root\",\"children\":[{\"value\":\"child\"}]}", treeNode.toJson());
        child.addChild(subChild);
        assertEquals(
                "{\"value\":\"root\",\"children\":[{\"value\":\"child\",\"children\":[{\"value\":\"subChild\"}]}]}",
                treeNode.toJson());
    }

    @Test
    public void testToString() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        final NaryTreeNode<String> subChild = new NaryTreeNode<>("subChild");
        assertEquals("NaryTreeNode{value=root, children=[]}", treeNode.toString());
        treeNode.addChild(child);
        assertEquals("NaryTreeNode{value=root, children=[NaryTreeNode{value=child, children=[]}]}", treeNode.toString());
        child.addChild(subChild);
        assertEquals("NaryTreeNode{value=root, children=[NaryTreeNode{value=child, children=[NaryTreeNode{value=subChild, children=[]}]}]}", treeNode.toString());
    }

    @Test
    public void toPrettyText() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child1 = new NaryTreeNode<>("child1");
        final NaryTreeNode<String> child2 = new NaryTreeNode<>("child2");
        final NaryTreeNode<String> child3 = new NaryTreeNode<>("child3");
        final NaryTreeNode<String> subChild11 = new NaryTreeNode<>("subChild11");
        final NaryTreeNode<String> subChild12 = new NaryTreeNode<>("subChild12");
        final NaryTreeNode<String> subChild21 = new NaryTreeNode<>("subChild21");
        final NaryTreeNode<String> subChild22 = new NaryTreeNode<>("subChild22");
        final NaryTreeNode<String> subSubChild211 = new NaryTreeNode<>("subSubChild211");
        assertEquals("""
                root
                 """, treeNode.toPrettyText());
        treeNode.addChild(child1);
        assertEquals("""
                root
                ├─child1
                 """, treeNode.toPrettyText());
        treeNode.addChild(child2);
        assertEquals("""
                root
                ├─child1
                ├─child2
                 """, treeNode.toPrettyText());
        child1.addChild(subChild11);
        child1.addChild(subChild12);
        assertEquals("""
                root
                ├─child1
                │ ├─subChild11
                │ ├─subChild12
                ├─child2
                                 """, treeNode.toPrettyText());
        child2.addChild(subChild21);
        child2.addChild(subChild22);
        assertEquals("""
                root
                ├─child1
                │ ├─subChild11
                │ ├─subChild12
                ├─child2
                │ ├─subChild21
                │ ├─subChild22
                 """, treeNode.toPrettyText());
        subChild21.addChild(subSubChild211);
        treeNode.addChild(child3);
        assertEquals("""
                root
                ├─child1
                │ ├─subChild11
                │ ├─subChild12
                ├─child2
                │ ├─subChild21
                │ │ ├─subSubChild211
                │ ├─subChild22
                ├─child3
                """, treeNode.toPrettyText());
    }

    @Test
    public void toPostfixList() {
        final NaryTreeNode<String> a = new NaryTreeNode<>("A");
        final NaryTreeNode<String> b = new NaryTreeNode<>("B");
        final NaryTreeNode<String> c = new NaryTreeNode<>("C");
        final NaryTreeNode<String> d = new NaryTreeNode<>("D");
        final NaryTreeNode<String> e = new NaryTreeNode<>("E");
        final NaryTreeNode<String> f = new NaryTreeNode<>("F");
        final NaryTreeNode<String> g = new NaryTreeNode<>("G");
        final NaryTreeNode<String> h = new NaryTreeNode<>("H");
        final NaryTreeNode<String> i = new NaryTreeNode<>("I");
        final NaryTreeNode<String> j = new NaryTreeNode<>("J");
        final NaryTreeNode<String> k = new NaryTreeNode<>("K");
        final NaryTreeNode<String> l = new NaryTreeNode<>("L");
        final NaryTreeNode<String> m = new NaryTreeNode<>("M");

        a.addChild(b);
        a.addChild(c);
        b.addChild(d);
        b.addChild(e);
        b.addChild(f);
        b.addChild(g);
        c.addChild(h);
        c.addChild(i);
        c.addChild(j);
        d.addChild(k);
        d.addChild(l);
        d.addChild(m);
        List<String> postFixListExpected = List.of("K", "L", "M", "D", "E", "F", "G", "B", "H", "I", "J", "C", "A");
        assertEquals(postFixListExpected, a.toPostfixList());
    }

    @Test
    public void toPrefixList() {
        final NaryTreeNode<String> a = new NaryTreeNode<>("A");
        final NaryTreeNode<String> b = new NaryTreeNode<>("B");
        final NaryTreeNode<String> c = new NaryTreeNode<>("C");
        final NaryTreeNode<String> d = new NaryTreeNode<>("D");
        final NaryTreeNode<String> e = new NaryTreeNode<>("E");
        final NaryTreeNode<String> f = new NaryTreeNode<>("F");
        final NaryTreeNode<String> g = new NaryTreeNode<>("G");
        final NaryTreeNode<String> h = new NaryTreeNode<>("H");
        final NaryTreeNode<String> i = new NaryTreeNode<>("I");
        final NaryTreeNode<String> j = new NaryTreeNode<>("J");
        final NaryTreeNode<String> k = new NaryTreeNode<>("K");
        final NaryTreeNode<String> l = new NaryTreeNode<>("L");
        final NaryTreeNode<String> m = new NaryTreeNode<>("M");

        a.addChild(b);
        a.addChild(c);
        b.addChild(d);
        b.addChild(e);
        b.addChild(f);
        b.addChild(g);
        c.addChild(h);
        c.addChild(i);
        c.addChild(j);
        d.addChild(k);
        d.addChild(l);
        d.addChild(m);
        List<String> prefixListExpected = List.of("A", "B", "D", "K", "L", "M", "E", "F", "G", "C", "H", "I", "J");
        assertEquals(prefixListExpected, a.toPrefixList());
    }

    @Test
    public void toByWidthList() {
        final NaryTreeNode<String> a = new NaryTreeNode<>("A");
        final NaryTreeNode<String> b = new NaryTreeNode<>("B");
        final NaryTreeNode<String> c = new NaryTreeNode<>("C");
        final NaryTreeNode<String> d = new NaryTreeNode<>("D");
        final NaryTreeNode<String> e = new NaryTreeNode<>("E");
        final NaryTreeNode<String> f = new NaryTreeNode<>("F");
        final NaryTreeNode<String> g = new NaryTreeNode<>("G");
        final NaryTreeNode<String> h = new NaryTreeNode<>("H");
        final NaryTreeNode<String> i = new NaryTreeNode<>("I");
        final NaryTreeNode<String> j = new NaryTreeNode<>("J");
        final NaryTreeNode<String> k = new NaryTreeNode<>("K");
        final NaryTreeNode<String> l = new NaryTreeNode<>("L");
        final NaryTreeNode<String> m = new NaryTreeNode<>("M");

        a.addChild(b);
        a.addChild(c);
        b.addChild(d);
        b.addChild(e);
        b.addChild(f);
        b.addChild(g);
        c.addChild(h);
        c.addChild(i);
        c.addChild(j);
        d.addChild(k);
        d.addChild(l);
        d.addChild(m);
        List<String> byWidthListExpected = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M");
        assertEquals(byWidthListExpected, a.toByWidthList());
    }

    @Test
    void getNodeFromElement() {
        final NaryTreeNode<String> a = new NaryTreeNode<>("A");
        final NaryTreeNode<String> b = new NaryTreeNode<>("B");
        final NaryTreeNode<String> c = new NaryTreeNode<>("C");
        final NaryTreeNode<String> d = new NaryTreeNode<>("D");
        final NaryTreeNode<String> e = new NaryTreeNode<>("E");
        final NaryTreeNode<String> f = new NaryTreeNode<>("F");
        final NaryTreeNode<String> g = new NaryTreeNode<>("G");
        final NaryTreeNode<String> h = new NaryTreeNode<>("H");
        final NaryTreeNode<String> i = new NaryTreeNode<>("I");
        final NaryTreeNode<String> j = new NaryTreeNode<>("J");
        final NaryTreeNode<String> k = new NaryTreeNode<>("K");
        final NaryTreeNode<String> l = new NaryTreeNode<>("L");
        final NaryTreeNode<String> m = new NaryTreeNode<>("M");

        a.addChild(b);
        a.addChild(c);
        b.addChild(d);
        b.addChild(e);
        b.addChild(f);
        b.addChild(g);
        c.addChild(h);
        c.addChild(i);
        c.addChild(j);
        d.addChild(k);
        d.addChild(l);
        d.addChild(m);
        assertEquals(a, a.getNodeFromElement("A"));
        assertEquals(b, a.getNodeFromElement("B"));
        assertEquals(c, a.getNodeFromElement("C"));
        assertEquals(d, a.getNodeFromElement("D"));
        assertEquals(e, a.getNodeFromElement("E"));
        assertEquals(f, a.getNodeFromElement("F"));
        assertEquals(g, a.getNodeFromElement("G"));
        assertEquals(h, a.getNodeFromElement("H"));
        assertEquals(i, a.getNodeFromElement("I"));
        assertEquals(j, a.getNodeFromElement("J"));
        assertEquals(k, a.getNodeFromElement("K"));
        assertEquals(l, a.getNodeFromElement("L"));
        assertEquals(m, a.getNodeFromElement("M"));

    }
}