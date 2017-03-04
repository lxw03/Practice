package com.example.cw.practice.practice.effectiveJava;

/**
 * Created by cw on 2017/3/4.
 */

public class UserBuilder {
    private final String name;
    private final int age;
    private final String eye;
    private final String mouth;

    public static class Builder{

        private final String name;

        //可选
        private int age = 0;
        private String eye = "2";
        private String mouth = "1";

        public Builder(String name) {
            this.name = name;
        }

        public Builder eye(String val){
            eye = val;
            return this;
        }

        public Builder mouth(String val){
            mouth = val;
            return this;
        }

        public Builder age(int val){
            age = val;
            return this;
        }

        public UserBuilder build(){
            return new UserBuilder(this);
        }
    }

    private UserBuilder(Builder builder) {
        name = builder.name;
        eye = builder.eye;
        age = builder.age;
        mouth = builder.mouth;
    }
}
