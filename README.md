# LispInterpreter
Scheme準拠のインタプリタ  

## 説明

### 組み込み手続き
* `+`
* `-`
* `*`
* `/`
* `=`
* `<`
* `<=`
* `>`
* `>=`
* `append`
* `car`
* `cdr`
* `cons`
* `cos`
* `display`
* `draw-line`
* `eq?`
* `exit`
* `list`
* `list?`
* `make-canvas`
* `map`
* `newline`
* `null?`
* `pair?`
* `sin`
* `sleep`
* `write`

### 特殊形式
* `define`
* `if`
* `lambda`
* `let`
* `quote`
* `set!`


## 使用法
### インストール
```sh
git clone https://github.com/sam0830/LispInterpriter.git
cd LispInterpreter
```

### ビルド
src/lisp内部のファイルをすべてコンパイルする必要がある。

### Run
```
java lisp.Main
```

## Hints
### Options
コマンドのオプションとか

### コマンドの実行結果の例


## 今後の方針
使用可能な手続きを増やし、多言語に移植する。

## Contribution
1. Fork it  
2. Create your feature branch  
3. Commit your changes  
4. Push to the branch  
5. Create new Pull Request

## License
[MIT](LICENSE)