# edifact-deserializer

Library for deserialization of EDIFACT files (eg. CREMUL, FINSTA, DEBMUL)


## Usage
Import the library into your existing java project.

### Maven
```
    <dependency>
        <groupId>com.bsgrd.treasurer.edifact</groupId>
        <artifactId>deserializer</artifactId>
        <version>2.0.0</version>
    </dependency>
```
### Gradle
```
    implementation: 'com.bsgrd.treasurer.edifact', name: 'deserializer', version: '2.0.0'
```

## Automatic syntax detection (service segment)
If the first line contains a service segment in the following format, it will be parsed and used for deserialization

`UNA:+,? '`

| Position | Example | Description              |
| -------- | ------- | ------------------------ |
| 0-2      | UNA     | Segment identifier       |
| 3-4      | :       | Composite data separator |
| 4-5      | +       | Data element separator   |
| 5-6      | ,       | Decimal notation         |
| 6-7      | ?       | Escape character         |
| 7-8      | empty   | Reserved - not used      |
| 9-10     | '       | Segment terminator       |

