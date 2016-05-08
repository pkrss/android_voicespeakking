//package com.pkrss.voicespeakking.db.generator;
//
//
//
//import de.greenrobot.daogenerator.DaoGenerator;
//import de.greenrobot.daogenerator.Entity;
//import de.greenrobot.daogenerator.Schema;
//
//public final class PkrssDaoGenerator {
//    public static void main(String[] args) throws Exception {
//        Schema schema = new Schema(1, "com.pkrss.voicespeakking.db.model");
//        schema.setDefaultJavaPackageDao("com.pkrss.voicespeakking.db.dao");
//        schema.enableKeepSectionsByDefault();
//        schema.enableActiveEntitiesByDefault();
//        //ActiveRecord
//        addEntity(schema);
//        new DaoGenerator().generateAll(schema, "./app/src/main/java");
//    }
//
//    private static void addEntity(Schema schema) {
//        Entity speakItem = schema.addEntity("SpeakItem");
//        speakItem.addIdProperty().primaryKey();
//        speakItem.addIntProperty("lastPos");
//        speakItem.addStringProperty("brief");
//        speakItem.addDateProperty("createTime");
//        speakItem.addDateProperty("updateTime");
//
//        Entity speakItemContent = schema.addEntity("SpeakItemContent");
//        speakItemContent.addIdProperty().primaryKey();
//        speakItemContent.addStringProperty("content");
//
//        Entity remoteCacheData = schema.addEntity("RemoteCacheData");
//        remoteCacheData.addIdProperty().primaryKey();
//        remoteCacheData.addStringProperty("url");
//        remoteCacheData.addStringProperty("content");
//        remoteCacheData.addDateProperty("createTime");
//    }
//}
