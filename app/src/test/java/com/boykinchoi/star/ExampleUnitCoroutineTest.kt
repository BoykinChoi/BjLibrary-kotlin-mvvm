package com.boykinchoi.star

import com.boykinchoi.star.design_pattern.abstract_factory.CarStore
import com.boykinchoi.star.design_pattern.adapter.BritishCharger
import com.boykinchoi.star.design_pattern.bridge.phone.AndroidPhone
import com.boykinchoi.star.design_pattern.bridge.phone.IPhone
import com.boykinchoi.star.design_pattern.bridge.software.Chrome
import com.boykinchoi.star.design_pattern.bridge.software.FireFox
import com.boykinchoi.star.design_pattern.builder.ToyotaCar
import com.boykinchoi.star.design_pattern.chain.Buyer
import com.boykinchoi.star.design_pattern.chain.carstore.BossMen
import com.boykinchoi.star.design_pattern.chain.carstore.DepartManager
import com.boykinchoi.star.design_pattern.chain.carstore.TeamLeader
import com.boykinchoi.star.design_pattern.command.game.RacingCar
import com.boykinchoi.star.design_pattern.command.handle.GamePad
import com.boykinchoi.star.design_pattern.decorator.PepperHerbaiJelly
import com.boykinchoi.star.design_pattern.decorator.CommonHerbaiJelly
import com.boykinchoi.star.design_pattern.decorator.HoneyHerbaiJelly
import com.boykinchoi.star.design_pattern.decorator.MilkHerbaiJelly
import com.boykinchoi.star.design_pattern.facade.Demander
import com.boykinchoi.star.design_pattern.facade.Demander2
import com.boykinchoi.star.design_pattern.factory.Car4Store
import com.boykinchoi.star.design_pattern.flyweight.DrawPicture
import com.boykinchoi.star.design_pattern.iterator.MusicPlayer1
import com.boykinchoi.star.design_pattern.iterator.MusicPlayer2
import com.boykinchoi.star.design_pattern.mediator.Mediator
import com.boykinchoi.star.design_pattern.mediator.Person
import com.boykinchoi.star.design_pattern.observer.User
import com.boykinchoi.star.design_pattern.prototype.Book
import com.boykinchoi.star.design_pattern.prototype.CBook
import com.boykinchoi.star.design_pattern.prototype.CloneTest
import com.boykinchoi.star.design_pattern.state.*
import com.boykinchoi.star.design_pattern.static_proxy.ChineseNetwork
import com.boykinchoi.star.design_pattern.static_proxy.JapaneseNetwork
import com.boykinchoi.star.design_pattern.static_proxy.WorldNetwork
import com.boykinchoi.star.design_pattern.static_proxy.delegate.DelegateMan
import com.boykinchoi.star.design_pattern.static_proxy.delegate.JTest
import com.boykinchoi.star.design_pattern.static_proxy.delegate.RealGamePlayer
import com.boykinchoi.star.design_pattern.strategy.CarWashCenter
import com.boykinchoi.star.design_pattern.visitor.Hotel
import com.boykinchoi.star.design_pattern.visitor.company.Alibaba
import com.boykinchoi.star.design_pattern.visitor.company.Tencent
import com.boykinchoi.star.design_pattern.visitor.visitor.NationalLeaderVisitor
import com.boykinchoi.star.design_pattern.visitor.visitor.ProvincialLeaderVisitor
import com.boykinchoi.star.myHandler.MyMessage
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitCoroutineTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * 简单工厂
     */
    @Test
    fun testFactoryPattern() {
        var car4Store = Car4Store()
        car4Store.saleCar(Car4Store.BRAND_BENZ)
    }

    /**
     * 抽象工厂
     */
    @Test
    fun testAbstractFactoryPattern() {
        var carStore = CarStore()
        carStore.saleCar(CarStore.BRAND_BMW)
    }

    /**
     * 建造者
     */
    @Test
    fun testBuilderPattern() {
        val car = ToyotaCar.Builder()
            .setEngine("v16发动机")
            .setColor("白色")
            .setGearbox("9AT")
            .setGrade("B")
            .setStyle("三厢车")
            .setWheel("米其林")
            .setPrice("10W")
            .build()
        println(car.engine + car.color + car.gearbox + car.grade + car.style + car.wheel + car.price)
    }

    /**
     * 策略+工厂模式
     */
    @Test
    fun testStrategyPattern() {
        var carWashCenter = CarWashCenter()
        carWashCenter.washCar(CarWashCenter.B)
    }

    /**
     * 责任链模式
     */
    @Test
    fun testChainPattern() {
        val xiaoMing = Buyer()
        val teamLeader = TeamLeader()
        val manager = DepartManager()
        val boss = BossMen()
        teamLeader.bindNextManager(manager)
        manager.bindNextManager(boss)

        teamLeader.permission(xiaoMing)

    }

    /**
     * 命令模式
     */
    @Test
    fun testCommandPattern() {
        val handHandle =
            GamePad()
//        val game = Contra()
        val game = RacingCar()
        handHandle.bindCommand(game)
        handHandle.clickSelectBtn()
        handHandle.clickStartBtn()
        handHandle.clickUpBtn()
        handHandle.clickLeftBtn()
        handHandle.clickRightBtn()
        handHandle.clickDownBtn()
        handHandle.clickBBtn()
        handHandle.clickABtn()
    }

    /**
     * 迭代器模式
     */
    @Test
    fun testIteratorPattern() {
        MusicPlayer1()
            .also {
                it.playMusicByLoop()
            }

        MusicPlayer2().also { it.playByLoop() }
    }

    /**
     * 中介者模式
     */
    @Test
    fun testMediatorPattern() {
        val xiaoming = Person("小明")
        xiaoming.lookingForJob(Mediator())
    }

    /**
     * 观察者模式
     */
    @Test
    fun testObserverPattern() {
        val dijia = User("迪迦")
        val oubu = User("欧布")
        val sailuo = User("赛罗")
        dijia.addObserver(oubu)
        dijia.addObserver(sailuo)
        dijia.sendMessage("今天打怪兽")

        oubu.addObserver(dijia)
        oubu.addObserver(sailuo)
        oubu.sendMessage("今天吃得好饱")

        sailuo.addObserver(dijia)
        sailuo.sendMessage("今天今天星闪闪~")
    }

    /**
     * 适配器模式
     */
    @Test
    fun testAdapterPattern() {
        val phone = BritishCharger()
//        val adapter = ChargerAdapter()
    }

    /**
     * 状态模式
     * 《阿里巴巴 Java 开发手册》：当超过 3 层的 if-else 的逻辑判断代码，推荐用状态模式来重构代码。
     * 或需要大量if else 时，也可用此模式
     */
    @Test
    fun testStatePattern() {
        val user = NewUser(NoneState())
        user.drawMoney(1000)
        user.setState(RegisterState())
        user.apply()
        user.setState(ApplyState())
        user.drawMoney(1000)
        user.setState(DrawState())
        user.drawMoney(1000)
    }

    /**
     * 装饰模式
     */
    @Test
    fun testDecoratorPattern() {
        // 这杯原味什么也没加
        val commonHerbaiJelly = CommonHerbaiJelly()
        val honeyHerbaiJelly = HoneyHerbaiJelly(commonHerbaiJelly)
        // 这杯加了蜂蜜
        honeyHerbaiJelly.process()
        val milkHerbaiJelly = MilkHerbaiJelly(honeyHerbaiJelly)
        // 这杯加了蜂蜜和牛奶
        milkHerbaiJelly.process()
        val blackFoodHerbaiJelly = PepperHerbaiJelly(milkHerbaiJelly)
        // 这杯加了蜂蜜和牛奶、再加辣椒
        blackFoodHerbaiJelly.process()

    }

    /**
     * 静态代理模式
     */
    @Test
    fun testStaticProxyPattern() {
        val worldNetwork = WorldNetwork()
        val chineseNetwork = ChineseNetwork(worldNetwork)
        chineseNetwork.access("www.google.com")
        chineseNetwork.access("www.baidu.com")
        chineseNetwork.access("www.facebook.com")
        chineseNetwork.access("www.ppp.com")
        chineseNetwork.access("www.sina.com")
        chineseNetwork.access("www.hahaha.com")

        val japaneseNetwork = JapaneseNetwork(worldNetwork)
        japaneseNetwork.access("www.google.com")
        japaneseNetwork.access("www.baidu.com")
        japaneseNetwork.access("www.facebook.com")
        japaneseNetwork.access("www.ppp.com")
        japaneseNetwork.access("www.sina.com")
        japaneseNetwork.access("www.hahaha.com")
    }

    /**
     * 外观模式
     */
    @Test
    fun testFacadePattern() {
        val demander = Demander()
        demander.deman("开发一个类似微信的系统，很简单嘛")
        val demander2 = Demander2()
        demander2.deman("开发一个类似淘宝的系统，半个月时间应该够了")
    }

    /**
     * 桥接模式
     */
    @Test
    fun testBridgePattern() {
        val xiaomi = AndroidPhone(Chrome())
        val iPhone = IPhone(FireFox())
        xiaomi.openSoftware()
        iPhone.openSoftware()
    }

    /**
     * 享元模式
     */
    @Test
    fun testFlyweightPattern() {
        val drawPicture = DrawPicture()
        drawPicture.drawByXiaoMing()
        drawPicture.drawByXiaoHong()
    }

    /**
     * 访问者模式
     */
    @Test
    fun testVisitorPattern() {

        val alibaba = Alibaba()
        val tencent = Tencent()

        val provincialLeaderVisitor = ProvincialLeaderVisitor()
        val nationalLeaderVisitor = NationalLeaderVisitor()

        val hotel = Hotel()
        hotel.add(alibaba)
        hotel.add(tencent)

        hotel.entertain(provincialLeaderVisitor)
        hotel.entertain(nationalLeaderVisitor)

    }

    /**
     * 原型模式
     */
    @Test
    fun testPrototypePattern() {
        CloneTest().apply { bookCreate() }
    }

    /**
     * 委托模式,使用kotlin的委托
     */
    @Test
    fun testDelegatePattern() {
        val gamePlayer = RealGamePlayer("代练大神")
        val delegateMan = DelegateMan(gamePlayer)
        delegateMan.rank()
        delegateMan.upgrade()

        println(JTest().prop)
        JTest().prop = "Hello,Thank you,Thank you very much"
    }

    @Test
    fun testMyHandler() {
        Thread {
            val msg = MyMessage()
            msg.obj = "gan gan gan"
            println("message send")
        }.start()
    }
}
