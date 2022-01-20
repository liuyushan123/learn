package com.liuyushan.spel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

import java.io.*;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

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

    @org.junit.jupiter.api.Test
    public void SPR9735eww() {
        Integer deli = Integer.valueOf(null);
        System.out.printf(deli + "");
    }


    @org.junit.jupiter.api.Test
    public <T> void SPR9735ewwasfda() {
        T[] da = (T[]) new Object[2];
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        System.out.println(classIntegerArrayList);
        System.out.println(classIntegerArrayList);
        System.out.println(classStringArrayList.equals(classIntegerArrayList));
    }

    class MainTest<T> {
        private T param;
    }

    @org.junit.jupiter.api.Test
    public void testType() {
        MainTest<String> data = new MainTest<String>() {
        };
        ParameterizedType genType1 = (ParameterizedType) data.getClass().getGenericSuperclass();
        System.out.println(genType1);
    }

    @org.junit.jupiter.api.Test
    public void json() {
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"\",\n" +
                "    \"data\": {\n" +
                "        \"session\": {\n" +
                "            \"token\": \"c14fd524f4dce3a800a0d3a22d6ce42e\"\n" +
                "        },\n" +
                "        \"user\": {\n" +
                "            \"id\": \"11\",\n" +
                "            \"name\": \"admin\",\n" +
                "            \"avatar\": \"http://192.168.0.214:8080/api/file/download/673166539845001217\",\n" +
                "            \"password_info\": {\n" +
                "                \"passwordStrength\": 1,\n" +
                "                \"passwordModified\": true\n" +
                "            }\n" +
                "        },\n" +
                "        \"role\": []\n" +
                "    }\n" +
                "}";
        String value1 = null;
        try {
            //将字符串转换成jsonObject对象
            JSONObject myJsonObject = new JSONObject(json);
            //获取对应的值
            JSONArray ed = myJsonObject.getJSONArray("ed");
            Object o = ed.get(1);
            JSONObject data = myJsonObject.getJSONObject("data");
            JSONObject user = data.getJSONObject("user");
            value1 = user.getString("name");
        } catch (JSONException e) {
            System.out.println(e);
        }
        System.out.println("value1=" + value1);
    }

    @org.junit.jupiter.api.Test
    public void json1() {
        String json = "{\"code\":0,\"msg\":\"\",\"data\":{\"rows\":[{\"arch_id\":\"669858121686798337\",\"name\":\"测试人员专用\",\"external_flag\":false},{\"arch_id\":\"669869028773023744\",\"name\":\"财务\",\"external_flag\":false},{\"arch_id\":\"669870321860169729\",\"name\":\"行政\",\"external_flag\":false},{\"arch_id\":\"669871384306733056\",\"name\":\"研发\",\"external_flag\":false},{\"arch_id\":\"669871557011394561\",\"name\":\"测试\",\"external_flag\":false},{\"arch_id\":\"669871673579491328\",\"name\":\"后勤\",\"external_flag\":false},{\"arch_id\":\"669871818807267329\",\"name\":\"运维\",\"external_flag\":false},{\"arch_id\":\"669871941008314368\",\"name\":\"安保\",\"external_flag\":false},{\"arch_id\":\"669872065969213441\",\"name\":\"硬件\",\"external_flag\":false},{\"arch_id\":\"669872184324083712\",\"name\":\"软件\",\"external_flag\":false},{\"arch_id\":\"669872307166859265\",\"name\":\"客服\",\"external_flag\":false},{\"arch_id\":\"671011965909639168\",\"name\":\"多的\",\"external_flag\":false},{\"arch_id\":\"672039160698413057\",\"name\":\"测试架构01\",\"external_flag\":false},{\"arch_id\":\"673550175169667073\",\"name\":\"节假日设置测试\",\"external_flag\":false},{\"arch_id\":\"675001466949459969\",\"name\":\"空架构\",\"external_flag\":false},{\"arch_id\":\"675026242438946816\",\"name\":\"操作日志123\",\"external_flag\":false},{\"arch_id\":\"688696530287415296\",\"name\":\"架构12\",\"external_flag\":false},{\"arch_id\":\"688717136907161601\",\"name\":\"架构76\",\"external_flag\":false},{\"arch_id\":\"688720003470675968\",\"name\":\"架构89\",\"external_flag\":false},{\"arch_id\":\"688725997605048321\",\"name\":\"架构24\",\"external_flag\":false},{\"arch_id\":\"688726838999207936\",\"name\":\"架构83\",\"external_flag\":false}]}}";
        String value1 = null;
        try {
            //将字符串转换成jsonObject对象
            JSONObject myJsonObject = new JSONObject(json);
            //获取对应的值
            JSONObject data = myJsonObject.getJSONObject("data");
            JSONArray rows = data.getJSONArray("rows");
            System.out.println(rows);
            List<Long> archIds = rows.toList().stream().map(t -> {
                if (t instanceof JSONObject) {
                    return ((JSONObject) t).getLong("arch_id");
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());

        } catch (JSONException e) {
            System.out.println(e);
        }
        System.out.println("value1=" + value1);
    }

    @org.junit.jupiter.api.Test
    public void write() throws IOException {
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File("F:\\write.csv");

        try{
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for(int i=1;i<=10;i++){
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write("新用户"+i+",男,"+(18+i));
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }

    }

    @org.junit.jupiter.api.Test
    public void test02(){
        String s = "{'mid': '16917', 'from': 'ACS701_1882', 'to': '1', 'time': 1633160189000, 'action': 300, 'data': {'cmd': 'door_checkin', 'payload': {'users': [{'user_id': '16917', 'check_type': 'fa', 'check_time': 1639524794, 'door_id': '674643301246427137', 'result': 0}, {'user_id': '16917', 'check_type': 'fa', 'check_time': 1639540468, 'door_id': '687701790688038912', 'result': 0}]}}}";
        String replace = s.replace("'", "\"");
        System.out.println(replace);
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
