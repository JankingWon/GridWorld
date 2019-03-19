//package Part3;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

class JumperTest {
    // 测试距jumper前面两个位置的格子有rock或者flower的情况
    @Test
    void testA() {
        //默认构造器构造world
        ActorWorld world = new ActorWorld();
        //构造一个jumper和rock和flower
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        Flower flower = new Flower();
        //将该jumper加到world里面
        world.add(new Location(2, 2),jumper);
        world.add(new Location(0, 2),rock);
        
        //断言jumper不能jump只能move
        Assert.assertEquals(false, jumper.canJump());
        Assert.assertEquals(true, jumper.canMove());
        
        //将rock换为flower
        world.remove(new Location(0, 2));
        world.add(new Location(0, 2), flower);
        
        //断言jumper能jump
        Assert.assertEquals(true, jumper.canJump());
        
    }
    
    // 测试距jumper前面两个位置的格子超过了grid的范围的情况
    @Test
    void testB() {
        final int x = 1, y = 1;
        //默认构造器构造world
        ActorWorld world = new ActorWorld();
        //构造一个jumper
        Jumper jumper = new Jumper();
        //将该jump加到world里面
        world.add(new Location(x, y),jumper);
        
        //断言jumper不能jump只能move
        Assert.assertEquals(false, jumper.canJump());
        Assert.assertEquals(true, jumper.canMove());
        
        //让jumper运动一次
        jumper.act();
        //断言jumper一次行为之后向右偏转了45度
        Assert.assertEquals(45, jumper.getDirection());
        
    }
    
    // 测试距jumper面对grid的边缘的情况
    @Test
    void testC() {
        final int x = 0, y = 1;
        //默认构造器构造world
        ActorWorld world = new ActorWorld();
        //构造一个jumper
        Jumper jumper = new Jumper();
        //将该jump加到world里面
        world.add(new Location(x, y),jumper);
        
        //断言jumper不能jump也不能move
        Assert.assertEquals(false, jumper.canJump());
        Assert.assertEquals(false, jumper.canMove());
        
    }
    
    // 测试距jumper前面两个格子的位置有其他actor（非Rock和flower）
    @Test
    void testD() {
        //默认构造器构造world
        ActorWorld world = new ActorWorld();
        //构造两个jumper
        Jumper jumper = new Jumper();
        Jumper jumper2 = new Jumper();
        //将两个jump加到world里面
        world.add(new Location(2, 2),jumper);
        world.add(new Location(0, 2),jumper2);
        
        //断言jumper不能jump只能move
        Assert.assertEquals(false, jumper.canJump());
        Assert.assertEquals(true, jumper.canMove());
        
        //让jumper运动一次
        jumper.act();
        //断言jumper一次行为之后向右偏转了45度
        Assert.assertEquals(45, jumper.getDirection());

    }

    // 测试距jumper前面一个格子的位置有其他jumper
    @Test
    void testE() {
        //默认构造器构造world
        ActorWorld world = new ActorWorld();
        //构造两个jumper
        Jumper jumper = new Jumper();
        Jumper jumper2 = new Jumper();
        //将两个jump加到world里面
        world.add(new Location(2, 2),jumper);
        world.add(new Location(1, 2),jumper2);
        
        //断言jumper仍然可以jump
        Assert.assertEquals(true, jumper.canJump());
        
    }

}
