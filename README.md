# AsciiGen
AsciiGen converts images to Ascii
## How to Use It
1. Download the source files
```
git clone https://github.com/frapalozz/AsciiGen.git
```
2. Compile the code
```
cd AsciiGen
javac Ascii.java
```
3. Execute
```
java Ascii demo.jpg 1
```
# How it Works
The first parameter is the path of the image
The second parameter is the invert flag:
- 1 -> Image color is inverted (black = " ")
- 0 -> Image color not inverted (black = "@") 