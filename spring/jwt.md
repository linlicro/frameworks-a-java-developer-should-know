# 理解JWT

看了[JSON Web Token - 在Web应用间安全地传递信息](http://blog.leapoahead.com/2015/09/06/understanding-jwt/)，知道了 JWT 协议是什么了，也知道了 `eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJxaWFubWlJZCI6InFtMTAzNTNzaEQiLCJpc3MiOiJhcHBfcW0xMDM1M3NoRCIsInBsYXRmb3JtIjoiYXBwIn0.cMNwyDTFVYMLL4e7ts50GFHTvlSJLDpePtHXzu7z9j4` 这样 A.B.C 的字符串时能敏感地认出这是使用了 jwt。

说说我理解的 JWT 优势。

* 无状态
* 有效避免了CSRF 攻击

也有问题:

* 无法作废已颁布的令牌，注销登录等场景下 token 还有效，被盗号了改密码也无法作废。
* token 的续签问题

适合的场景:

* 一次性验证: 比如用户注册后需要发一封邮件让其激活账户。

参考

* [Introduction to JSON Web Tokens](https://jwt.io/introduction/)
