package com.kingcall.html;

import com.kingcall.file.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlParse {
    public static void main(String[] args) {
       String path= "/Users/liuwenqiang/Desktop/1.html";
        parseHtml(path,"kingcall");
    }

    public static void parseHtml(String path,String tableName){
        String html = readHtmlFile(path);
        Document document = Jsoup.parse(html);
        Element table=pasreTable(document);
        tableToSql(table, tableName);
    }

    public static String readHtmlFile(String path){
        return FileUtils.ReadFile(path);
    }

    public static Element pasreTable(Document document){
        Elements elements = document.select("table");
        Element table = elements.first();
        return table;
    }

    public static List<String> tableToSql(Element table,String tableName){
        List<List<String>> data = getTableContexts(table);
        List<String> listSql = new ArrayList<String>();
        // 在这里完成sql 的拼接,完成字段名称的拼接
        if (data.size()<2){
            throw new RuntimeException("no data to parse");
        }
        String columns = "(";
        for (String line : data.get(0)) {
            columns = columns + "`" + line + "`"+",";
        }
        columns = columns.substring(0,columns.length() -1) + ") VALUES(";
        String base = String.format("insert into %s", tableName) + columns;
        // 完成字段值的拼接
        for (int i = 1; i < data.size(); i++) {
            List<String> values=data.get(i);
            String value = values.stream().map(line -> "'" + line + "'").collect(Collectors.toList()).toString();
            listSql.add(base + value.substring(1, value.length() - 1) + ");");
        }
        return listSql;
    }


    public static String getValue(Element e) {
        return e.attr("value");
    }

    public static String getText(Element e) {
        return e.text();
    }

    public static List<List<String>> getTableContexts(Element table){
        List<List<String>> data = new ArrayList<>();
        int index = 1;
        String query = "th";
        for (Element etr : table.select("tr")) {
            List<String> list = new ArrayList<>();
            if (index>1){
                query = "td";
            }
            for (Element etd : etr.select(query)) {
                String temp = etd.text();
                //增加一行中的一列
                list.add(temp);
            }
            //增加一行
            data.add(list);
            index++;
        }
        return data;
    }


}
