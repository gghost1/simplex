# Simplex method program
The program solves the avarage LLM problems
## How to use
- Clone the project
- Set up Java (version 22) and Maven
- Run the program
- Input the problem in the correct way (next paragraph)
- If the problem is not unbounded, you will see the list of scales of the init function and the answer
## Correct input of the problem
### The problem should be in this format:
Maximize <init function z (z = <your function\>)\>\
Bounded by:\
(your functions in the format of inequality with operand <=)\
- <b> If you have minimization problem, you should multiply your init function by -1</b>
- <b> If in your bounds the operand is >=, then you should multiply this inequality by -1</b>
