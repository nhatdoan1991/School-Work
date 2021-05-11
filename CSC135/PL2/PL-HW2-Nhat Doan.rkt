;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |PL-HW2-Nhat Doan|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;Nhat Doan CSC 135-Meilu 
;1>volume = 4 * 3.14 * r3 / 3: function sphere-volume takes in a radius as parameter and return the volume of the sphere 
(define (sphere-volume radius)
   (exact->inexact (/ (* 4 3.14 (expt radius 3))3))); return the volume of the sphere with the given radius
(define (shell-volume inner-radius outer-radius)
   (- (exact->inexact (/ (* 4 3.14 (expt outer-radius 3))3)) (exact->inexact (/ (* 4 3.14 (expt inner-radius 3))3)))) ; return the volume of the ring with the given inner and outer radiuses
;2> functuion close-with-limit takes three arguments -- number-1, number-2, and limit -- and returns #t if the two numbers are within a distance of limit from each other
(define (close num1 num2)
	(or (< (abs (- num1 num2)) 0.001) (= (abs (- num1 num2)) 0.001)); return true if the distance of 2 number is less than or equal to 0.001
)
(define (close-with-limit number-1 number-2 limit)
    (or (< (abs (- number-1 number-2)) limit) (= (abs (- number-1 number-2)) limit)); return true if the distance of 2 number is less than or equal to the limit
)
;3>the function how-many, which consumes the coefficients a, b, and c of a proper quadratic equation and determines how many solutions the equation has
(define (how-many a b c)
	(if (> (* b b) (* 4 a c))	; check if b^2 > 4ac
	2				;return 2 if so
	(if(< (* b b) (* 4 a c))	;if not, check if b^2 < 4ac
	0				;return 0 if so
	1))				;return 1 if not
)
;4> Function filter-out-symbol take 2 argument a list and a symbol, then return the list with all the matching symbols removed 
(define (filter-out-symbol list symbol)
	(cond ((null? list) '()) 	;check if the list is null, return empty list if so
	((eq? symbol (car list))	; else check if the first element of the list equal to the symbol
	(filter-out-symbol (cdr list) symbol))	;if so, recurse the function by passing a new list without the first symbol
	(else (cons (car list) (filter-out-symbol (cdr list) symbol)))); else concatinate the first element with the recursive function by passing a new list without the first symbol
)
	
;5>Function pMinMax take in an argument a list of integer, then return the min and max
(define (Min L)
  (cond ((null? (cdr L)) (car L)) ; check if the list without the first symbol is the last symbol, return that symbol if so
        ((<(car L)(Min (cdr L)))(car L));else check if the first symbol is less than the recursive of the next elements on the list, return that element if it is less than the rest of the element
        (else (Min (cdr L))))); else recurse to find the next smallest integer.
(define (Max L)
  (cond ((null? (cdr L))(car L));check if the list without the first symbol is the last symbol, return that symbol if so
        ((> (car L)(Max (cdr L))) (car L));else check if the first symbol is greater than the recursive of the next elements on the list, return that element if it is greater than the rest of the element
        (else(Max( cdr L))))); else recurse to find the next largest integer.
(define (pMinMax L)
  (list(Min L)(Max L))); return the list of min and max
;6>Higher-order function incnth that takes an integer n as a parameter and returns an n-th increment function, which increments its parameter by n 
(define (incnth n)
  (lambda (x)(+ x n)))	; return the sum of them