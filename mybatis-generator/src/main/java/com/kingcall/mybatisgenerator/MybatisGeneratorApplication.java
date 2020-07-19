package com.kingcall.mybatisgenerator;

import com.kingcall.mybatisgenerator.entity.Coffee;
import com.kingcall.mybatisgenerator.entity.CoffeeExample;
import com.kingcall.mybatisgenerator.mapper.CoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("com.kingcall.mybatisgenerator.mapper;")
public class MybatisGeneratorApplication implements ApplicationRunner{

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
		//generateArtifacts();
        playWithArtifacts();
    }

    /**
     * 需要注意的是用maven插件的方式可以不用先建sql 文件存放的文件夹，但是用代码得先建
     * @throws Exception
     */
    private void generateArtifacts() throws Exception {
        System.out.println("已经被执行");
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generator/generatorConfigByCode.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void playWithArtifacts() {
        Coffee espresso = new Coffee()
                .withName("espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(espresso);

        Coffee latte = new Coffee()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeMapper.insert(latte);

        Coffee s = coffeeMapper.selectByPrimaryKey(1L);
        log.info("Coffee {}", s);

        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("latte");
        List<Coffee> list = coffeeMapper.selectByExample(example);
        list.forEach(e -> log.info("selectByExample: {}", e));
    }

}
