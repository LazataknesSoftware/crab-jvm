[Русский](#о-программе) | [English](#about)

<a href="#о-программе"></a>

# О программе
Crab - это создатель аббревиатур, который был создан на двух языках программирования: Go и Java. Первая версия на Go была создана в 2024 году, а название программы было подобрано самой программой: она сократила ключевые слова "create" и "abbreviations", что в результате привело к "crab".

# Чем отличается от [версии на Go](https://github.com/LazataknesSoftware/crab)?
Первое и самое главное отличие - стабильность. Версия на Java безотказно работает в отличие от версии на Go, где по загадочным обстоятельствам иногда не работают переключатели командной строки.

Второе отличие - поддержка русского языка. Версия на Java позволяет создавать аббревиатуры из ключевых слов на русском языке, в версии на Go подобное мне повторить не удалось из-за "чудес" с переключателями.

Но у этой версии есть небольшие проблемы, которые, однако, не мешают:
* Иногда может вылезти пустота вместо аббревиатуры. У меня такое было, когда я воспользовался всеми переключателями.
* В отличие от версии на Go, не следит за синтаксисом словаря, то есть может допустить пустые линии, что приведет к ошибке. Лучше заранее удалить отсюда пустые линии. А еще эта версия не следит за порядком сокращений в словаре, то есть сокращения, состоящие из одного слова, могут идти перед сокращениями из двух слов, что может привести к неожиданным результатам. Поэтому сокращения, состоящих более чем из одного слова, ставьте в самом верху словаря, а сокращения из одного слова оставляйте внизу.
* При создании аббревиатур на русском языке `-Dnoprefix` бесполезен.

# Зависимости
* Java 17

# Переключатели командной строки

Так как программа работает на Java Virtual Machine, то аргументы передаются так: `-D<переключатель>=<значение>`. Переключатели должны передаваться всегда после `java.exe`, перед `-jar` и ключевыми словами, например:

> `java -Drepeat=20 -Dshuffle=true -jar crab.jar "ключевые слова..."`

### `-Dnodict`
* **Функция:** позволяет игнорировать словарь
* **Принимаемые типы значений:** `true`/`false`

### `-Drepeat`
* **Функция:** позволяет генерировать аббревиатуры указанное количество раз
* **Принимаемые типы значений:** число

### `-Dexclude`
* **Функция:** позволяет исключить некоторые ключевые слова
* **Принимаемые типы значений:** `true`/`false`

### `-Dshort`
* **Функция:** максимально сокращает ключевые слова
* **Принимаемые типы значений:** `true`/`false`

### `-Dnoprefix`
* **Функция:** удаляет английские предлоги
* **Принимаемые типы значений:** `true`/`false`
* **Примечание:** при ключевых словах на русском языке этот переключатель бесполезен

### `-Dshuffle`
* **Функция:** перемешивает ключевые слова
* **Принимаемые типы значений:** `true`/`false`

# Как правильно добавлять свои сокращения в словарь
1. Посмотрите, из какого количества слов состоит сокращение. Если оно состоит более чем из одного слова, то поместите его вверху, иначе если оно состоит из одного слова, то поместите его внизу:
```
eta - time left
n - do not
n - number
n - no
```
2. Внимательно следите за тем, чтобы в `dict.txt` не было пустых линий, которые помешают программе.
3. Помните, что допускаются и сокращения, которые могут иметь несколько значений (пример выше).
4. Удаляя сокращение, не допускайте пустых линий в `dict.txt`.
5. Не пытайтесь поместить в одну строку несколько вариантов сокращений: `V - volume, version`
6. :warning: **Не удаляйте `dict.txt`!** Там содержатся основные сокращения на английском языке. Без `dict.txt` программа не сможет запуститься, однако это можно обойти через `-Dnodict=true`.

---

<a href="#about"></a>

# About
Crab is an abbreviation creator created in two programming languages: Go and Java. The first version in Go was created in 2024, and the program's name was chosen by the program itself: it shortened the keywords "create" and "abbreviations" resulting in "crab".

# Differences with [Go version](https://github.com/LazataknesSoftware/crab)
The first and most important difference is stability. The Java version works flawlessly, unlike the Go version, where command-line switches sometimes mysteriously fail to work.

The second difference is Russian language support. The Java version allows you to create abbreviations from Russian keywords, but I couldn't replicate this in the Go version due to the mystery with switches.

This version does have some minor issues, but they don't interfere:
* Sometimes you might get empty strings instead of abbreviations. I experienced this when I used all the switches.
* Unlike the Go version, it doesn't maintain dictionary syntax, meaning it may allow empty lines, which will cause an error. It's best to remove empty lines from here beforehand. This version also doesn't maintain the order of abbreviations in the dictionary, meaning single-word abbreviations may appear before two-word abbreviations, which can lead to unexpected results. Therefore, place abbreviations consisting of more than one word at the very top of the dictionary, and leave single-word abbreviations at the bottom.
* When creating abbreviations in Russian, `-Dnoprefix` is useless.

# Dependencies
* Java 17

# Command-line switches

Since the program runs on the Java Virtual Machine, arguments are passed as follows: `-D<switch>=<value>`. Switches should always be passed after `java.exe`, before `-jar` and keywords, for example:

> `java -Drepeat=20 -Dshuffle=true -jar crab.jar "keywords..."`

### `-Dnodict`
* **Function:** allows to ignore dictionary
* **Allowed value types:** `true`/`false`

### `-Drepeat`
* **Function:** allows to generate abbreviations the specified number of times
* **Allowed value types:** number

### `-Dexclude`
* **Function:** allows to exclude some keywords
* **Allowed value types:** `true`/`false`

### `-Dshort`
* **Function:** maximally shortens keywords
* **Allowed value types:** `true`/`false`

### `-Dnoprefix`
* **Function:** remove English prepositions
* **Allowed value types:** `true`/`false`
* **Note:** this switch is useless when generating abbreviations with Russian keywords
### `-Dshuffle`
* **Function:** shuffles keywords
* **Allowed value types:** `true`/`false`

# How to add my shortenings to dictionary correctly?
1. Look at how many words the abbreviation consists of. If it consists of more than one word, place it at the top; if it consists of one word, place it at the bottom:
```
eta - time left
n - do not
n - number
n - no
```
2. Be careful that there are no blank lines in `dict.txt` that will interfere with the program.
3. Remember that abbreviations that can have multiple meanings are also allowed (see example above).
4. When deleting an abbreviation, avoid blank lines in `dict.txt`.
5. Don't try to fit multiple abbreviations on a single line: `V - volume, version`
6. :warning: **Don't delete `dict.txt`!** It contains the main abbreviations in English. Without `dict.txt`, the program won't start, but this can be bypassed with `-Dnodict=true`.