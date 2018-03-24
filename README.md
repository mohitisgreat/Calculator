# Calculator
A next generation calculator for complex mathematical calculations. It has a very good parses to parse the mathematical expression and a very simple abstract syntax tree completely written in Java.

* **Variable Declaration**: You can declare your own variable as
<code><pre>
x = 3+4
y = x*x
</pre></code>
will output as `49.0`

* **Built-in Functions**: There are many built-in functions for complex calculaions like:

    * `abs(Number)`: Returns the absoulute value. For example `abs(-4.0)` retunrs 4.0.

    * Trignometric functions as `sin, cos, tan, cot, sec, cosec`.

    * `sqr(Number)` for squaring the number and `sqrt(Number)` for square roots.

    * `cube(Number)` for cubing the number and `cbrt` for cube roots.

## Building from source code
It is very easy to build from source code and run. You need the following softwares

* Gradle
* JDK
* Git

Follow these steps to build the Calculator

* Clone the fresh repository using `git clone https://github.com/mohitisgreat/Calculator`

* Navigate to the repository directoy using `cd Calculator`
* Now build the software using `gradle build`
* And run it using `gradle run`
