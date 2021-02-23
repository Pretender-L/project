package com.project.design.patterns.bulider;

public class Bulider {

    public static void main(String[] args) {
        Bulider bulider = new Bulider();
        bulider.test();
    }

    public void test(){
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        concreteBuilder.partABulider();
        concreteBuilder.partBBulider();
        Director director = new Director(concreteBuilder);
        Product result = director.getResult();
        System.out.println(result.toString());
    }
    //项目
    class Product {
        //项目组件
        private String partA;
        private String partB;

        public String getPartA() {
            return partA;
        }

        public void setPartA(String partA) {
            this.partA = partA;
        }

        public String getPartB() {
            return partB;
        }

        public void setPartB(String partB) {
            this.partB = partB;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "partA='" + partA + '\'' +
                    ", partB='" + partB + '\'' +
                    '}';
        }
    }

    abstract class BuilderTest {
        protected Product product = new Product();

        abstract void partABulider();

        abstract void partBBulider();

        Product getProduct() {
            return product;
        }
    }

    //施工员
    class ConcreteBuilder extends BuilderTest {

        @Override
        void partABulider() {
            product.setPartA("内部组件A构造成功");
        }

        @Override
        void partBBulider() {
            product.setPartB("内部组件B构造成功");
        }
    }

    //项目经理
    class Director {
        private BuilderTest builderTest;

        public Director(BuilderTest builderTest) {
            this.builderTest = builderTest;
        }

        public Product getResult() {
            builderTest.partABulider();
            builderTest.partBBulider();
            return builderTest.getProduct();
        }
    }

}
