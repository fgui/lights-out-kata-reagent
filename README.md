# lights out UI kata

inspired by https://github.com/xpmatteo/lights-out-kata

## Description

The goal of this exercise is to learn how to write a web page with ClojureScript and Reagent.

Lights Out is a simple puzzle.  In this version of the puzzle, there is a 3x3 grid that looks like this:

    1 1 1
    1 1 1
    1 1 1

The elements of the grid can change state from 1 to 0 and from 0 to 1.

Whenever you click on a number, that element and the ones above, below, right and left of it change state.  For instance, if I click on the center element, the grid becomes

    1 0 1
    0 0 0
    1 0 1

And if I then click on the top left element, the grid becomes

    0 1 1
    1 0 0
    1 0 1

The object of the game is to set all the elements to 0.

## Suggested steps

-  start with a single light and turning on/off just one light.
-  create a vector of light where you can turn on/off a single light.
-  create a 3x3 grid where you can turn on/off a single light.
-  implement turn on/off the light + neighbors on click.
-  for fun:
  -  have more than one game in a single page.
  -  have 2 connected games in the page.
  -  any size grid.

## Instructions

```
  lein new reagent lights-out
  lein figwheel
  edit src/cljs/lights_out/core.cljs
```

  Delete most of the contents of core.cljs to start from cero.
  Start with something like:
  
```clojure
(ns lights-out.core
    (:require [reagent.core :as reagent :refer [atom]]))

;; -------------------------
;; Views

(defn current-page []
  [:div [:h1 "lights out!"]])

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
```
  
   
