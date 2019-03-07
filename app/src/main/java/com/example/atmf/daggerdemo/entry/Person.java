package com.example.atmf.daggerdemo.entry;

public class Person{
      private   String name;
      private   String age;
      private   String clazz;
      private   String sex;
      private   String hight;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHight() {
            return hight;
        }

        public void setHight(String hight) {
            this.hight = hight;
        }

        private Person(Builder builder){
          this.age=builder.age;
          this.clazz=builder.clazz;
          this.hight=builder.hight;
          this.sex=builder.sex;
          this.name=builder.name;
      }

     public static class Builder{
          private   String name;
          private   String age;
          private   String clazz;
          private   String sex;
          private   String hight;

          public Builder name(String name){
              this.name=name;
              return this;
          }
         public Builder age(String age){
              this.age=age;
              return this;
          }
         public Builder clazz(String clazz){
              this.clazz=clazz;
              return this;
          }
         public Builder sex(String sex){
              this.sex=sex;
              return this;
          }
         public Builder hight(String hight){
              this.hight=hight;
              return this;
          }
         public Person build(){
              return new Person(this);
          }

        }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", clazz='" + clazz + '\'' +
                ", sex='" + sex + '\'' +
                ", hight='" + hight + '\'' +
                '}';
    }
}