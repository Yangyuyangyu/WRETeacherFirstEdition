package com.wr_education.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13.
 */
public class HomePageTwoListBean {

    /**
     * code : 0
     * msg : 数据获取成功
     * data : [{"id":"64","cid":"18","tid":"22","sid":"32,28,27","status":"0","date":"2016-05-09","start":"17:30:00","place":"菜单","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"测试课程A","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/07/1462612726.jpg","type":"1","class_time":"2016-05-09 17:30~18:30"},{"id":"63","cid":"18","tid":"22","sid":"32,28,27","status":"0","date":"2016-05-08","start":"17:30:00","place":"菜单","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"测试课程A","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/07/1462612726.jpg","type":"1","class_time":"2016-05-08 17:30~18:30"},{"id":"62","cid":"18","tid":"22","sid":"32,28,27","status":"0","date":"2016-05-07","start":"17:30:00","place":"菜单","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"测试课程A","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/07/1462612726.jpg","type":"1","class_time":"2016-05-07 17:30~18:30"},{"id":"61","cid":"18","tid":"22","sid":"32,28,27","status":"0","date":"2016-05-06","start":"17:30:00","place":"菜单","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"测试课程A","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/07/1462612726.jpg","type":"1","class_time":"2016-05-06 17:30~18:30"},{"id":"60","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-31","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-31 15:00~16:00"},{"id":"59","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-30","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-30 15:00~16:00"},{"id":"58","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-29","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-29 15:00~16:00"},{"id":"57","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-28","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-28 15:00~16:00"},{"id":"56","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-27","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-27 15:00~16:00"},{"id":"55","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-26","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-26 15:00~16:00"},{"id":"54","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-25","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-25 15:00~16:00"},{"id":"53","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-24","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-24 15:00~16:00"},{"id":"52","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-23","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-23 15:00~16:00"},{"id":"51","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-22","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-22 15:00~16:00"},{"id":"50","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-21","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-21 15:00~16:00"},{"id":"49","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-20","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-20 15:00~16:00"},{"id":"48","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-19","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-19 15:00~16:00"},{"id":"47","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-18","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-18 15:00~16:00"},{"id":"46","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-17","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-17 15:00~16:00"},{"id":"45","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-16","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-16 15:00~16:00"},{"id":"44","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-15","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"12","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-15 15:00~16:00"},{"id":"43","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-14","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"12","absent":"","confirm":"1","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-14 15:00~16:00"},{"id":"42","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-13","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"12","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-13 15:00~16:00"},{"id":"41","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-12","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"12","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-12 15:00~16:00"},{"id":"37","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-08","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-08 15:00~16:00"},{"id":"36","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-07","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-07 15:00~16:00"},{"id":"35","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-06","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-06 15:00~16:00"},{"id":"34","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-05","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-05 15:00~16:00"},{"id":"33","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-04","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-04 15:00~16:00"},{"id":"32","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-03","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-03 15:00~16:00"},{"id":"31","cid":"14","tid":"22","sid":"12","status":"0","date":"2016-05-02","start":"15:00:00","place":"成都市青羊区上南大街2号","present":"12","absent":"","confirm":"0","longitude":"","latitude":"","name":"数学","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/04/1462353528.jpg","type":"1","class_time":"2016-05-02 15:00~16:00"},{"id":"30","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-31","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-31 18:00~19:00"},{"id":"29","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-30","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-30 18:00~19:00"},{"id":"28","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-29","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-29 18:00~19:00"},{"id":"27","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-28","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-28 18:00~19:00"},{"id":"26","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-27","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-27 18:00~19:00"},{"id":"25","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-26","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-26 18:00~19:00"},{"id":"24","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-25","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-25 18:00~19:00"},{"id":"23","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-24","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-24 18:00~19:00"},{"id":"22","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-23","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-23 18:00~19:00"},{"id":"21","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-22","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-22 18:00~19:00"},{"id":"20","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-21","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-21 18:00~19:00"},{"id":"19","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-20","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-20 18:00~19:00"},{"id":"18","cid":"17","tid":"22","sid":"28","status":"0","date":"2016-05-19","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-19 18:00~19:00"},{"id":"17","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-18","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-18 18:00~19:00"},{"id":"16","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-17","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-17 18:00~19:00"},{"id":"15","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-16","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-16 18:00~19:00"},{"id":"14","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-15","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-15 18:00~19:00"},{"id":"13","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-14","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-14 18:00~19:00"},{"id":"11","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-12","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-12 18:00~19:00"},{"id":"10","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-11","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-11 18:00~19:00"},{"id":"9","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-10","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-10 18:00~19:00"},{"id":"8","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-09","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-09 18:00~19:00"},{"id":"7","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-08","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-08 18:00~19:00"},{"id":"6","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-07","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-07 18:00~19:00"},{"id":"5","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-06","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-06 18:00~19:00"},{"id":"4","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-05","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-05 18:00~19:00"},{"id":"3","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-04","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-04 18:00~19:00"},{"id":"2","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-03","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-03 18:00~19:00"},{"id":"1","cid":"17","tid":"22","sid":"26","status":"0","date":"2016-05-02","start":"18:00:00","place":"123132123","present":"26","absent":"","confirm":"0","longitude":"","latitude":"","name":"联调课程1","img":"http://192.168.10.143/weirao/public/upload/images/admin/1/201605/06/1462529051.jpg","type":"2","class_time":"2016-05-02 18:00~19:00"}]
     */

    private int code;
    private String msg;
    /**
     * id : 64
     * cid : 18
     * tid : 22
     * sid : 32,28,27
     * status : 0
     * date : 2016-05-09
     * start : 17:30:00
     * place : 菜单
     * present :
     * absent :
     * confirm : 0
     * longitude :
     * latitude :
     * name : 测试课程A
     * img : http://192.168.10.143/weirao/public/upload/images/admin/1/201605/07/1462612726.jpg
     * type : 1
     * class_time : 2016-05-09 17:30~18:30
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String cid;
        private String tid;
        private String sid;
        private String status;
        private String date;
        private String start;
        private String place;
        private String present;
        private String absent;
        private String confirm;
        private String longitude;
        private String latitude;
        private String name;
        private String img;
        private String type;
        private String class_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getPresent() {
            return present;
        }

        public void setPresent(String present) {
            this.present = present;
        }

        public String getAbsent() {
            return absent;
        }

        public void setAbsent(String absent) {
            this.absent = absent;
        }

        public String getConfirm() {
            return confirm;
        }

        public void setConfirm(String confirm) {
            this.confirm = confirm;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClass_time() {
            return class_time;
        }

        public void setClass_time(String class_time) {
            this.class_time = class_time;
        }
    }
}
