import scala.annotation.tailrec

@tailrec
def sum(n: Int, res: Int): Int = {
  if (n == 1) res
  else sum(n - 1, res + n)
}

@tailrec
def sum(n: Int): Int = {
  if (n == 1) 1
  else sum(n - 1) + n
}

sum(9999999, 0)