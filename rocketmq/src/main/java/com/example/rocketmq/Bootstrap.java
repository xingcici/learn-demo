package com.example.rocketmq;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : Bootstrap v0.1 2020/6/30 19:15 haifeng.pang Exp $
 **/
public class Bootstrap {

    public static void main(String[] args) throws Exception{
        MqConsumer consumer = new MqConsumer();
        consumer.init();
        consumer.startup();

        //MqProducer producer = new MqProducer();
        //producer.init();
        //producer.startup();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                //producer.shutdown();
                consumer.shutdown();
            }
        }));
    }

    /*public <T> long executeFetch(String tableName, int batchSize, Function<Record, T> dataParser, Function<List<T>, Boolean> dataStorage) throws Exception {
        // 构建下载会话
        DownloadSession session = buildSession(tableName);

        // 获取数据数量
        long recordCount = session.getRecordCount();
        if (recordCount == 0) {
            return 0;
        }

        // 进行数据读取
        long fetchCount = 0L;
        try (RecordReader reader = session.openRecordReader(0L, recordCount, true)) {
            // 依次读取数据
            Record record;
            List<T> dataList = new ArrayList<>(batchSize);
            while ((record = reader.read()) != null) {
                // 解析添加数据
                T data = dataParser.apply(record);
                if (Objects.nonNull(data)) {
                    dataList.add(data);
                }

                // 批量存储数据
                if (dataList.size() == batchSize) {
                    Boolean isContinue = dataStorage.apply(dataList);
                    fetchCount += batchSize;
                    dataList.clear();
                    if (!Boolean.TRUE.equals(isContinue)) {
                        break;
                    }
                }
            }

            // 存储剩余数据
            if (CollectionUtils.isNotEmpty(dataList)) {
                dataStorage.apply(dataList);
                fetchCount += dataList.size();
                dataList.clear();
            }
        }

        // 返回获取数量
        return fetchCount;
    }

    // 使用案例
    long fetchCount = odpsService.executeFetch("user", 5000, record -> {
        UserDO user = new UserDO();
        user.setId(record.getBigint("id"));
        user.setName(record.getString("name"));
        return user;
    }, dataList -> {
        userDAO.batchInsert(dataList);
        return true;
    });*/
}
