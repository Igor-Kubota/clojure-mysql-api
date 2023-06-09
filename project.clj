(defproject clojure-mysql-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [ring/ring-core "1.10.0"]
                 [lynxeyes/dotenv "1.1.0"]
                 [ring/ring-jetty-adapter "1.10.0"]
                 [compojure "1.6.2"]
                 [mysql/mysql-connector-java "8.0.21"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler clojure-mysql-api.core/app
         :init clojure-mysql-api.core/init}
  :main clojure-mysql-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
