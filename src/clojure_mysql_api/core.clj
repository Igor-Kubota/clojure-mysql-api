(ns clojure-mysql-api.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
          [ring.util.response :refer [response]]
          [dotenv :refer [env app-env]]
          [compojure.route :as route]
          [compojure.core :refer [defroutes GET]]
          [clojure.java.jdbc :as jdbc]))




(def db {
  :classname "com.mysql.cj.jdbc.Driver"
  :subprotocol "mysql"
  :subname (str "//" (env "DB-HOST") ":" (env "DB-PASSWORD")  "/" (env "DB-NAME")  "?useTimeZone=true&serverTimezone=UTC") 
  :user (env "USER")
  :password (env "PASSWORD")
})

(defn execute-query [query]
  (jdbc/query db query))

(defn get-cursos []
  (execute-query ["SELECT * FROM cursos"]))

(defn get-curso-by-id [id]
  (execute-query ["SELECT * FROM cursos WHERE idCurso = ?" id]))

(defroutes app-routes
  (GET "/cursos" [] (response (get-cursos)))
  (GET "/curso/:id" [id] (response (get-curso-by-id id)))
  (route/not-found "Not found"))

(defn -main
  "Starts the API server"
  [& args]
  (jetty/run-jetty app-routes {:port 3000}))