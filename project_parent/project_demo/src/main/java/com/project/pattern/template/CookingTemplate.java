package com.project.pattern.template;

/**
 * @author wjl
 */
public abstract class CookingTemplate {
    /**
     * 具体的整个过程
     */
    protected void cooking(){
        this.preparation();
        this.doing();
        this.carriedDishes();
    }

    /**
     * 备料
     */
    public abstract void preparation();
    /**
     * 做菜
     */
    public abstract void doing();
    /**
     * 上菜
     */
    public abstract void carriedDishes ();
}
