package com.boykinchoi.star

import com.boykinchoi.star.coroutine.CoroutineTest
import com.boykinchoi.star.design_pattern.abstract_factory.CarStore
import com.boykinchoi.star.design_pattern.adapter.animal.AnimalFriendAdapter
import com.boykinchoi.star.design_pattern.adapter.animal.Cat
import com.boykinchoi.star.design_pattern.adapter.animal.Dog
import com.boykinchoi.star.design_pattern.adapter.animal.Man
import com.boykinchoi.star.design_pattern.adapter.charger.BritishCharger
import com.boykinchoi.star.design_pattern.adapter.charger.BritishPhone
import com.boykinchoi.star.design_pattern.adapter.charger.ChargerAdapter
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
import com.boykinchoi.star.design_pattern.prototype.CloneTest
import com.boykinchoi.star.design_pattern.state.*
import com.boykinchoi.star.design_pattern.proxy.static_p.ChineseNetwork
import com.boykinchoi.star.design_pattern.proxy.static_p.JapaneseNetwork
import com.boykinchoi.star.design_pattern.proxy.static_p.WorldNetwork
import com.boykinchoi.star.design_pattern.proxy.delegate.DelegateMan
import com.boykinchoi.star.design_pattern.proxy.delegate.JTest
import com.boykinchoi.star.design_pattern.proxy.delegate.RealGamePlayer
import com.boykinchoi.star.design_pattern.proxy.dynamic_p.DynamicProxyTest
import com.boykinchoi.star.design_pattern.strategy.CarWashCenter
import com.boykinchoi.star.design_pattern.visitor.Hotel
import com.boykinchoi.star.design_pattern.visitor.company.Alibaba
import com.boykinchoi.star.design_pattern.visitor.company.Tencent
import com.boykinchoi.star.design_pattern.visitor.visitor.NationalLeaderVisitor
import com.boykinchoi.star.design_pattern.visitor.visitor.ProvincialLeaderVisitor
import com.boykinchoi.star.myHandler.MyMessage
import com.boykinchoi.star.reflect.ReflectTest
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
        val adapter =
            ChargerAdapter(BritishCharger())
        val phone = BritishPhone(adapter)
        phone.chargeing()

        Man().apply {
            speakTo(AnimalFriendAdapter(Dog()))
            speakTo(AnimalFriendAdapter(Cat()))
        }
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
     * 动态代理模式
     */
    @Test
    fun testDynamicProxyPattern(){
        DynamicProxyTest().apply {
            test()
        }
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
    fun testCollection() {
        println("-------------for--------------------")
        for(i in 1..6) print(i)
        println("")
        for(i in 7 downTo 1) print(i)
        println("")
        println("-------------plus--------minus--------------------")
        val numbers = listOf("one", "two", "three", "four")
        // plus
        val plusList = numbers + "five"
        println(plusList)
        // minus
        val minusList = numbers - listOf("three", "four")
        println(minusList)
        println("-----------------过滤 filter------------------------")
        // 过滤 filter
        println(numbers.filter { it.length > 4 })
        println(numbers.filterNot { it.length == 4 })
        println(numbers.filterIndexed { index, s -> index > 2 })
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
        println(filteredMap)

        println("----------------划分 partition------------------------")
        // 划分 partition 过滤集合并且将不匹配的元素存放在一个单独的列表中
        val (match,rest) = numbers.partition { it.length>3 }
        println(match)
        println(rest)

        println("----------------检验------------------------")
        // 检验
        // 如果至少有一个元素匹配给定谓词，那么 any() 返回 true。
        // 如果没有元素与给定谓词匹配，那么 none() 返回 true。
        // 如果所有元素都匹配给定谓词，那么 all() 返回 true。注意，在一个空集合上使用任何有效的谓词去调用 all() 都会返回 true
        // any() 和 none() 也可以不带谓词使用：在这种情况下它们只是用来检查集合是否为空
        println(numbers.any { it.endsWith("e") })
        println(numbers.none { it.endsWith("a") })
        println(numbers.all { it.endsWith("e") })

        println("----------------分组 groupBy------------------------")
        val numbers2 = listOf("one", "two", "three", "four", "five", "six")
        // 分组 groupBy
        println(numbers2.groupBy { it.first().toUpperCase() })
        println(
            numbers2.groupBy(
                keySelector = { it.first() },
                valueTransform = { it.toUpperCase() })
        )
        println("--------------groupBy eachCount 统计分组数量---------------")
        //groupBy eachCount 统计分组数量
        println(numbers2.groupingBy { it.first() }.eachCount())

        println("--------------取集合一部分--------------")
        //slice() 返回具有给定索引的集合元素列表。 索引既可以是作为区间传入的也可以是作为整数值的集合传入的
        println(numbers2.slice(1..3))
        println(numbers2.slice(1..5 step 2))
        println(numbers2.slice(setOf(2,3,5)))

        println("------------take--drop---takeWhile-----dropWhile-------")
        //take 从头开始获取指定数量的元素,akeLast从最后开始获取指定数量的元素
        println(numbers2.take(4))
        println(numbers2.takeLast(4))
        println(numbers2.drop(1))
        println(numbers2.dropLast(3))

        //takeWhile它将不停获取元素直到排除与谓词匹配的首个元素,(就是一直取出元素直到条件成立那个元素则停止取出)
        println(numbers2.takeWhile { !it.startsWith('f') })
        println(numbers2.takeLastWhile { it != "three" })
        println(numbers2.dropWhile { it.length == 3 })
        println(numbers2.dropLastWhile { it.contains('i') })

    }

    @Test
    fun testReflect(){
        ReflectTest().run {
//            printFields()
//            printMethods()
//             getPrivateMethods()
            modifyPrivateFiled()
        }
    }

    @Test
    fun testCoroutine(){
        CoroutineTest().run {
            //testCoroutineContext()
        }
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
