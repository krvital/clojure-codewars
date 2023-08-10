(ns core
  (:require [clojure.string :as str]))

;;;; Opposite number

;; Very simple, given an interger or a floating-point number, find its opposite

;; My initial solution
;; (defn opposite-number [n]
;;   (* -1 n))

;; I could do this also
;; (defn opposite-number [n]
;;   (- n))

;; The best solution
(def opposite-number -)

(opposite-number -15.0)

;; --------------------

;;;; Sum of positive

;; You get an array of numbers, return the sum of all of the positives ones.
;; Example [1,-4,7,12] => 1 + 7 + 12 = 20
;; Note: if there is nothing to sum, the sum is default to 0.

;; My initial solution
(defn sum-of-positive [nums]
  (->> nums
       (filter pos?)
       (reduce +)))

(sum-of-positive [1, -4, 7, 12])
(sum-of-positive [-1, -4, -7, -12])

;; --------------------

;;;; Grasshoper - Summation

;; Write a program that finds the summation of every number from 1 to num. The number will always be a positive integer greater than 0.
;; For example (Input -> Output):
;; 2 -> 3 (1 + 2)
;; 8 -> 36 (1 + 2 + 3 + 4 + 5 + 6 + 7 + 8)

;; My initial solution
;; (defn summation [n]
;;   (loop [i 1
;;          result 0]
;;     (if (<= i n)
;;       (recur (inc i) (+ result i))
;;       result)))

;; How could not I figured out that I need to use range
;; (defn summation [n]
;;   (reduce + (range (inc n))))

;; There is also variation with apply instead of reduce
(defn summation [n]
  (apply + (range (inc n))))

;; And there is also math solution for this which I simply forgot
;; (defn summation [n]
;;   (/
;;    (* n (inc n))
;;    2))

(summation 22)

;; --------------------

;;;; Convert number to reversed array of digits
;; Given a random non-negative number, you have to return the digits of this number within an array in reverse order.
;; Example(Input => Output):

;; 35231 => [1,3,2,5,3]
;; 0 => [0]

;; My initial solution. A naive one
;; (defn digitize [n]
;;   (if (zero? n)
;;     [0]
;;     (loop [m n
;;            result []]
;;       (if (zero? m)
;;         result
;;         (recur (quot m 10) (conj result (rem m 10)))))))

;; Solution with str which I also thought about. I just did know how to do it without clojure.string

(defn digitize [n]
  (->> (str n)
       (reverse)
       (map #(Integer/parseInt (str %)))))

(digitize 35231)

(digitize 0)

;; --------------------

;;;; Is n divisible by x and y?
;; Create a function that checks if a number n is divisible by two numbers x AND y. All inputs are positive, non-zero numbers.

;; Examples:
;; 1) n =   3, x = 1, y = 3 =>  true because   3 is divisible by 1 and 3
;; 2) n =  12, x = 2, y = 6 =>  true because  12 is divisible by 2 and 6
;; 3) n = 100, x = 5, y = 3 => false because 100 is not divisible by 3
;; 4) n =  12, x = 7, y = 5 => false because  12 is neither divisible by 7 nor 5

;; My solution
(defn divisible? [n x y]
  (and (zero? (rem n x))
       (zero? (rem n y))))

;; Another possible solution
;; (defn divisible? [n x y]
;;   (= 0 (mod n x) (mod n y)))

(divisible? 12 2 6)

;; --------------------

;;;; Returning Strings
;; Make a function that will return a greeting statement that uses an input; your program should return, "Hello, <name> how are you doing today?".
;; [Make sure you type the exact thing I wrote or the program may not execute properly]

(defn greet [name]
  (str "Hello, " name " how are you doing today?"))

(greet "Vitaly")

;; --------------------

;;;; Get the Middle Character (7 kyu)

;; You are going to be given a word. Your job is to return the middle character of the word. If the word's length is odd, return the middle character. If the word's length is even, return the middle 2 characters.

;; Examples:
;; Kata.getMiddle("test") should return "es"
;; Kata.getMiddle("testing") should return "t"
;; Kata.getMiddle("middle") should return "dd"
;; Kata.getMiddle("A") should return "A"

;; #Input
;; A word (string) of length 0 < str < 1000 (In javascript you may get slightly more than 1000 in some test cases due to an error in the test cases). You do not need to test for this. This is only here to tell you that you do not need to worry about your solution timing out.

;; The middle character(s) of the word represented as a string.

;; My initial solution
;; (defn get-middle [s]
;;   (let [odd-len? (odd? (count s))
;;         middle (quot (count s) 2)]
;;     (if odd-len?
;;       (str (nth s middle))
;;       (str (nth s (dec middle)) (nth s middle)))))

;; I could use subs to get substring
(defn get-middle [s]
  (let [cnt (count s)]
    (subs s (quot (dec cnt) 2) (quot (+ cnt 2) 2))))

(get-middle "test") ;; -> "es"
(get-middle "testi") ;; -> "s"

;; --------------------

;;;; Two to One

;; Take 2 strings s1 and s2 including only letters from a to z. Return a new sorted string, the longest possible, containing distinct letters - each taken only once - coming from s1 or s2.

;; Examples:

;; a = "xyaabbbccccdefww"
;; b = "xxxxyyyyabklmopq"
;; longest(a, b) -> "abcdefklmopqwxy"

;; a = "abcdefghijklmnopqrstuvwxyz"
;; longest(a, a) -> "abcdefghijklmnopqrstuvwxyz"

;; My initial solution
;; (defn longest [s1 s2]
;;   (apply str
;;          (-> (str s1 s2)
;;              distinct
;;              sort)))

;; In order to use thread operator I could wrap in ()
(defn longest [s1 s2]
  (->> (str s1 s2)
       (distinct)
       (sort)
       (apply str)))

(longest  "xyaabbbccccdefww"  "xxxxyyyyabklmopq")

;; --------------------

;;;; Mumbling

;; This time no story, no theory. The examples below show you how to write function accum:
;; Examples:

;; accum("abcd") -> "A-Bb-Ccc-Dddd"
;; accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
;; accum("cwAt") -> "C-Ww-Aaa-Tttt"

;; The parameter of accum is a string which includes only letters from a..z and A..Z.

;; My initial solution
(defn accum [s]
  (->> s
       (map-indexed
        (fn [idx val] (repeat (inc idx) val)))
       (map #(apply str %))
       (map str/capitalize)
       (str/join "-")))

(accum "abcd") ;; "A-Bb-Ccc-Dddd"
(accum "RqaEzty") ;; "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"

;; --------------------

;;;; Jaden-cased

;; Jaden Smith, the son of Will Smith, is the star of films such as The Karate Kid (2010) and After Earth (2013). Jaden is also known for some of his philosophy that he delivers via Twitter. When writing on Twitter, he is known for almost always capitalizing every word. For simplicity, you'll have to capitalize each word, check out how contractions are expected to be in the example below.

;; Your task is to convert strings to how they would be written by Jaden Smith. The strings are actual quotes from Jaden Smith, but they are not capitalized in the same way he originally typed them.

;; Example:
;; Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
;; Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"

;; My initial solution.
;; It turned out that it was common solution. Anotgher way of doing this is
(defn jaden-cased [s]
  (->> (str/split s #"\s")
       (map str/capitalize)
       (str/join " ")))

(jaden-cased "How can mirrors be real if our eyes aren't real")

;; --------------------

;;;; Shortest word

;; Simple, given a string of words, return the length of the shortest word(s).
;; String will never be empty and you do not need to account for different data types.

(defn shortest-word [s]
  (->> (str/split s #"\s")
       (map count)
       (apply min)))

(shortest-word
 "bitcoin take over the world maybe who knows perhaps") ;; -> 3

;; --------------------

;;;; Multiples of 3 or 5

;; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;; Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in. Additionally, if the number is negative, return 0 (for languages that do have them).
;; Note: If the number is a multiple of both 3 and 5, only count it once.

(defn multiples-of-3-or-5 [number]
  (->> (range number)
       (filter #(or (zero? (mod % 3)) (zero? (mod % 5))))
       (apply +)))

(multiples-of-3-or-5 -10)

;; --------------------

;;;; Stop gninnipS My sdroW! (6 kyu)

;;Write a function that takes in a string of one or more words, and returns the same string, but with all five or more letter words reversed (Just like the name of this Kata). Strings passed in will consist of only letters and spaces. Spaces will be included only when more than one word is present.

;; Examples:

;; spinWords( "Hey fellow warriors" ) => returns "Hey wollef sroirraw"
;; spinWords( "This is a test") => returns "This is a test"
;; spinWords( "This is another test" )=> returns "This is rehtona test"

;; My initial solution
(defn spin-words [s]
  (->> (str/split s #"\s")
       (map (fn [word] (if (>= (count word) 5)
                         (apply str (reverse word))
                         word)))
       (str/join " ")))

;; Solution with regexp
;; (defn spin-words [s]
;;   (str/replace s #"\w{5,}" str/reverse))

(spin-words "Hey fellow warriors")

;; --------------------

;;;; Persistent Bugger (6 kyu)

;; Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence, which is the number of times you must multiply the digits in num until you reach a single digit.

;; For example (Input --> Output):

;; 39 --> 3 (because 3*9 = 27, 2*7 = 14, 1*4 = 4 and 4 has only one digit)
;; 999 --> 4 (because 9*9*9 = 729, 7*2*9 = 126, 1*2*6 = 12, and finally 1*2 = 2)
;; 4 --> 0 (because 4 is already a one-digit number)

;; My initial solution
(defn persistence [n]
  (let [one-digit? (fn [n] (zero? (quot n 10)))
        produce (fn [n] (->>
                         (str/split (str n) #"")
                         (map #(Integer/parseInt %))
                         (apply *)))]
    (loop [result 0
           m n]
      (if (one-digit? m)
        result
        (recur (inc result) (produce m))))))

(= (persistence 39) 3)

;; --------------------

;;;; Duplicate Encoder

;; The goal of this exercise is to convert a string to a new string where each character in the new string is "(" if that character appears only once in the original string, or ")" if that character appears more than once in the original string. Ignore capitalization when determining if a character is a duplicate.

;; Examples
;; "din"      =>  "((("
;; "recede"   =>  "()()()"
;; "Success"  =>  ")())())"
;; "(( @"     =>  "))(("

;; Notes
;; Assertion messages may be unclear about what they display in some languages. If you read "...It Should encode XXX", the "XXX" is the expected result, not the input!

;; My initial solution. I did not know about frequencies function
;; But generally the idea of solution looks like in my function.
(defn dups-set [word]
  (->> word
       (reduce (fn [acc c] (if (contains? acc c)
                             (assoc acc c true)
                             (assoc acc c false))) {})
       (filter (fn [[k v]] (true? v)))
       (map first)
       set))

(defn encode-dups [text]
  (let [text (str/lower-case text)
        dups-set' (dups-set text)]
    (->> text
         (map #(if (contains? dups-set' %) ")" "("))
         (apply str))))

(encode-dups "din") ;; -> "((("
(encode-dups "recede") ;; -> "()()()"

;; --------------------
