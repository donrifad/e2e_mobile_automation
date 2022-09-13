 
Note:
=====
This is sample template of a framework for the mobile automation using Appium.This framework allow testers to run the same tests
in the android and ios platform.Page objects are seperated and tests are combined.


<h2>Getting Started</h2>

<br>Project is a maven project </br>
<br>Install mvn in your local machine </br>
<br>Install java </br>
<br>Install git and Intellij</br> 
<br>Check out this project </br>
<br>Build your project </br>
<br>Make sure to add your apps and xpaths since it is a template<br>
<br>Install appium <br>



<h2>Run your test</h2>
<br>mvn clean test</br>
<br>mvn clean test -Pregressionui -Dsuite=smoke</br>

<br>You can right click on any of the tests and can run as testNG test.</br>
<br>Test results will be available at test out put folder.</br>


<h2>Project Structure</h2>

<br>Tests and Functions are separated.</br>
<br>All tests available in test package</br>
<br>In main package we have 5 sub packages</br>
````
├── apps
|  ├── proverbial_android.apk
|  └── proverbial_ios.ipa
├── pom.xml
└── src
|  ├── main
|  |  ├── java
|  |  |  └── com
|  |  |     └── qa
|  |  |        ├── common
|  |  |        |  ├── ConfigPropertReader.java
|  |  |        |  ├── GlobalConstants.java
|  |  |        |  ├── Menus.java
|  |  |        |  └── TestRailPropertyReader.java
|  |  |        ├── data
|  |  |        |  ├── Address.java
|  |  |        |  ├── AlertNames.java
|  |  |        |  ├── ErrorMessages.java
|  |  |        |  ├── User.java
|  |  |        |  └── UserAccount.java
|  |  |        ├── functions
|  |  |        |  ├── FunctionBase.java
|  |  |        |  ├── Login.java
|  |  |        |  └── PageFactory.java
|  |  |        ├── pages
|  |  |        |  ├── AbstractPages
|  |  |        |  |  └── LoginPage.java
|  |  |        |  ├── Android
|  |  |        |  |  └── login
|  |  |        |  ├── DriverPageUtil.java - Use full helper method to intract with elements
|  |  |        |  └── IOS
|  |  |        |     └── login
|  |  |        └── utils
|  |  |           ├── AppiumUtil.java
|  |  |           ├── CapabilityUtil.java
|  |  |           ├── DriverSetupUtil.java
|  |  |           ├── HeadSpinUtil.java
|  |  |           ├── TestRailUtil.java
|  |  |           └── XpathBuilder.java
|  |  └── resources
|  |     ├── config.properties  - Provide all configurations related to your execution
|  |     ├── log4j.xml
|  |     ├── reportportal.properties  - Connecting report portal
|  |     └── testrail.properties
|  └── test
|     ├── Smoke.xml
|     └── java
````

<h2>Configurations</h2>
<br>Open config.properties file resources folder</br>
{android,ios,browser}

```
platform=ios
```

{local/remote-{browser stack , slendoid}/remote_head_spin}

```
run.mode=remote_head_spin
```

<h3>ios/android head spin configurations</h3>
<br>Ios configurations you can change the  device id  here</br>

```
ios.head.spin.device.udid=00008101-00144C3E21grgrg98001E


ios.head.spin.device=iPhone 12

bundleId=com.harley-davidson.maxwell

ios.version=14

install.ios_app.headspin=false

ios.device=iPhone 12
```

#### Android configurations
````
device=SM-G991U1

os_version=11

android.head.spin.device.udid=R3CNggrgrgC082X5Y

android.head.spin.device=SM-G991U1
````

<h2> Reporting </h2>

<br>Report portal integration is done here https://reportportal.io/ </br>
<br> Host the report portal in any of your servers and provide your configurations </br>
<br> Open the report portal.properties and provide your configuration details </br>
<br> Report portal dependencies are added these attributes coming from report portal </br>
<br> Report portal lisnter is added in smoke.xml file </br>
````

    @TestCaseId("A5,B5")
    @Attributes(attributes = {@Attribute(key = "suite", value = "Smoke shared"),
            @Attribute(key = "feature", value = "Logging in,Logging out"),
            @Attribute(key = "priority", value = "High")})
    @Test(priority = 1)
    public void testUserIsAbleToLoginWithoutAnyIssues()
````


