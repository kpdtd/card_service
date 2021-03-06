# language: zh-CN

功能: 流量卡充值支付订单测试

  背景:
    假如用户"aa"存在一张iccid为"bb13811155196"的大象卡
    而且使用手机号"13811155888"进行了实名认证
    而且该卡对应的账户信息主账户金额为"0"
    而且该卡存在当月的套餐信息为月租"10"元,日租宝为"1"元"1024"M
    而且服务器已经配置了金额为"10"元的充值列表
    而且生成订单号为"T201808101538560727T6tWk5ITI"的订单,支付金额为"10"元,订单类型为"1"

  场景: 当服务器接收到微信公众号支付回传信息时
    假如服务器成功接收到微信公众号支付回传信息
    而且服务器接收到订单为"T201808101538560727T6tWk5ITI"的支付报文
    当访问服务器"wxPayCallback"回传接口时
    那么订单"T201808101538560727T6tWk5ITI"的状态应该为"4"
    那么该卡对应的用户账户主账户金额应该为"1000"分