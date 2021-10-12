package com.demo.javademo.io.excel;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class ExcelReaderTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExcelReaderTest.class);
    private List<MyEntity> buffer = new Vector<>(1000);

    public static void main(String[] args) throws Exception {
        ExcelReaderTest test = new ExcelReaderTest();
        List<MyEntity> entityList = test.readSpu_tag();
        entityList.forEach(System.out::print);
    }

    public List<MyEntity> readSpu_tag() throws Exception {
        Excel2EntityConfig config = new Excel2EntityConfig();
        String[] columns = {"spuid", "tagid", "sql"};
        config.setColumns(columns);
        // 设置日期的格式，和Excel里的日期格式一至
//        config.setFormater(new SimpleDateFormat("yyyy.MM.dd"));
        // 设置从第2行开始读，忽略前1行
        config.setCurrPosittion(2);
        //设置从第1列开始读取
//			config.setColStartPosittion(1);
        ExcelReader<MyEntity> excel = new ExcelReader<>();
        excel.setExcel2EntityConfig(config);

        //如果现现EXCEl编码问题引起的读取问题，请将InputStream换成 ByteArrayInputStream 可解决问题
        File file = ResourceUtils.getFile("classpath:TestSpuidsFor.xlsx");
        logger.info("路径:{}", file.getPath());
        excel.initExcelReader(file.getPath());
        MyEntity entity = new MyEntity();
        excel.setEntity(entity);
        entity = excel.readLine();
        int count = 0;
        long sum = 0;
        long startTime = System.currentTimeMillis();
        while (entity != null) {
            //计数实体
            logger.info("" + entity);
            if (entity.getSpuid() != null && entity.getTagid() != null) {
                buffer.add(entity);
            } else {
                logger.warn("" + entity);
                break;
            }
            count++;
            if (count % 500 == 0) {
                sum += buffer.size();
                buffer.clear();
            }
            excel.setEntity(new MyEntity());
            entity = excel.readLine();
        }
        if (buffer.size() > 0) {
            sum += buffer.size();
            buffer.clear();
        }
        logger.info("总计读入条数：" + sum + " 共计耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        return buffer;
    }

    public class MyEntity implements Serializable {
        private static final long serialVersionUID = 1L;
        private Long spuid;
        private Long tagid;
        private String sql;

        public Long getSpuid() {
            return spuid;
        }

        public void setSpuid(Long spuid) {
            this.spuid = spuid;
        }

        public Long getTagid() {
            return tagid;
        }

        public void setTagid(Long tagid) {
            this.tagid = tagid;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        @Override
        public String toString() {
            return "MyEntity [spuid=" + spuid + ", tagid=" + tagid + ", sql=" + sql + "]";
        }
    }
}
