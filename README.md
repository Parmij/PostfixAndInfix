# Sam Media Hacker Test

link for test question:

https://gist.github.com/homam/25dc665325a12aeb06aeed9dc66ada91

## Solution:

Steps:

-- step 1
```
Initialize the game for set operators (+ , - , *), range the numbers and set level
```
-- step 2
```
generate random postfix 
```

-- step3
```
convert postfix to infix with add necessary parentheses
```

-- step4
```
evaluate postfix expression to check with player input
note: you can make evaluate in the same method generate random postfix (just you need small editing)
```

-- step5
```
Check answer the play if correct or wrong
```

# note:
-question and bouns1 solve together in same code.

-bouns2: 
```
level 1: o(n) ---> 100
level 2: o * n*(n+1) / 2 
         note: o = o (if operand '+' or '*'), o = 2 * o (if operand '-' or '/')
level 3: I guess more than 2^o * n(n+1)(2n + 1) / 6
level 4: I guess more than 2^o * O(n^4)
```
         



