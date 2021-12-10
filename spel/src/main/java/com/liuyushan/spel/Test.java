package com.liuyushan.spel;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

/**
 * @author lys
 * 2021/10/10
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test {

    @Autowired
    public StudentDao studentDao;

    @org.junit.jupiter.api.Test
    public void test() {
        String s = "hello world";
        String concat = s.concat("!");
        System.out.println(concat);
    }

    @org.junit.jupiter.api.Test
    public void testSpel() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

    @org.junit.jupiter.api.Test
    public void testSpel2() {
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

// The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("'name:'+name");
        String name = (String) exp.getValue(tesla);
        System.out.println(name);
// name == "Nikola Tesla"

        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        System.out.println(result);
    }

    @org.junit.jupiter.api.Test
    public void SPR9735() {
        Item item = new Item();
        item.setName("parent");

        Item item1 = new Item();
        item1.setName("child1");

        Item item2 = new Item();
        item2.setName("child2");

        item.add(item1);
        item.add(item2);

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        Expression exp = parser.parseExpression("#item[0].name");
        context.setVariable("item", item);
        Object value = exp.getValue(context);
        System.out.println(value);
        //assertEquals("child1", exp.getValue(context));
    }

    class Item implements List<Item> {

        private String name;

        private List<Item> children = new ArrayList<Item>();

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public int size() {
            return this.children.size();
        }

        @Override
        public boolean isEmpty() {
            return this.children.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return this.children.contains(o);
        }

        @Override
        public Iterator<Item> iterator() {
            return this.children.iterator();
        }

        @Override
        public Object[] toArray() {
            return this.children.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return this.children.toArray(a);
        }

        @Override
        public boolean add(Item e) {
            return this.children.add(e);
        }

        @Override
        public boolean remove(Object o) {
            return this.children.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return this.children.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends Item> c) {
            return this.children.addAll(c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends Item> c) {
            return this.children.addAll(index, c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return this.children.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return this.children.retainAll(c);
        }

        @Override
        public void clear() {
            this.children.clear();
        }

        @Override
        public Item get(int index) {
            return this.children.get(index);
        }

        @Override
        public Item set(int index, Item element) {
            return this.children.set(index, element);
        }

        @Override
        public void add(int index, Item element) {
            this.children.add(index, element);
        }

        @Override
        public Item remove(int index) {
            return this.children.remove(index);
        }

        @Override
        public int indexOf(Object o) {
            return this.children.indexOf(o);
        }

        @Override
        public int lastIndexOf(Object o) {
            return this.children.lastIndexOf(o);
        }

        @Override
        public ListIterator<Item> listIterator() {
            return this.children.listIterator();
        }

        @Override
        public ListIterator<Item> listIterator(int index) {
            return this.children.listIterator(index);
        }

        @Override
        public List<Item> subList(int fromIndex, int toIndex) {
            return this.children.subList(fromIndex, toIndex);
        }
    }

    @org.junit.jupiter.api.Test
    public void testLog() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        String expressionString = "(#b == true ? '#currentUserName' : '#currentUserNo') + 'ainio'";
        String s = "T(com.liuyushan.spel.FeatureTypeEnum).valueOf('FA').title";
        String s1 = "#studentDao.getName()";
        //String s1 = "@studentDao.getName()";
        Object currentUserName = "未知用户";
        Object currentUserNo = "未知编号";
        boolean b = true;
        ctx.setVariable("currentUserName", currentUserName);
        ctx.setVariable("currentUserNo", currentUserNo);
        ctx.setVariable("b", b);
        ctx.setVariable("studentDao", studentDao);
        ctx.setBeanResolver(new BeanFactoryResolver(factory));
        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Object expressionResult = expressionParser.parseExpression(expressionString).getValue(ctx);
        Object o = expressionParser.parseExpression(s).getValue(ctx);
        String expressionStr = null == expressionResult ? "" : expressionResult.toString();
        System.out.println(expressionStr);
        Object o1 = expressionParser.parseExpression(s1).getValue(ctx);
        System.out.println(o);
        System.out.println(o1);
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        System.out.println("hello world");
    }


    public static void test2(String s) {
        System.out.println("hello world 2");
        System.out.println(s);
    }

    @org.junit.jupiter.api.Test
    public void testStudent() {
        System.out.println(studentDao.getName());
    }

    @org.junit.jupiter.api.Test
    public void test3(){
        String s = "11";
        String substring = s.substring(0, 255);
        System.out.println(substring);
    }
}
