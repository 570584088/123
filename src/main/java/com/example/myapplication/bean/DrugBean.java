package com.example.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13/013.
 */

public class DrugBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * title : 感冒
         * first : 1
         * content : [{"num":20,"id":7,"name":"支气管炎"},{"num":20,"id":6,"name":"止咳化痰"},{"num":20,"id":5,"name":"上火"},{"num":16,"id":2,"name":"伤寒感冒"},{"num":15,"id":9,"name":"牙痛"},{"num":15,"id":1,"name":"发烧"},{"num":13,"id":10,"name":"头痛"},{"num":13,"id":8,"name":"过敏性哮喘"},{"num":11,"id":11,"name":"风热祛暑"},{"num":10,"id":12,"name":"解热镇痛"},{"num":10,"id":4,"name":"咳嗽"},{"num":9,"id":3,"name":"流行感冒"}]
         */

        private String title;
        private int first;
        private List<ContentBean> content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * num : 20
             * id : 7
             * name : 支气管炎
             */

            private int num;
            private int id;
            private String name;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
