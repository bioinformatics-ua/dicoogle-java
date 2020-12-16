
# dicoogle-java: dicoogle API client


**dicoogle-java** is the official `dicoogle`_ API client for Java.


No longer mantained, check the fork: https://github.com/BMDSoftware/dicoogle-java


*Open Source Project Medical Imaging Repositories using Indexing System and P2P mechanisms*

www.dicoogle.com


How to use? 
---------

If you use maven: 
```
 <dependency>
  <groupId>pt.ua.ieeta</groupId>
  <artifactId>Dicoogle-Java</artifactId>
  <version>0.5</version>
</dependency>
```

In your application: 
```
DicoogleClient client = new DicoogleClient("http://localhost:6060/");
List<Image> images = client.searchFreeText("CT", QueryLevel.IMAGE, false);
System.out.println(client.dump("0.0.0.0.1.8811.2.1.20010413115754.12432"));
```




Resources
---------

* www.dicoogle.com

License
-------

GPL v3


