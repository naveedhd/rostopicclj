(ns rostopic
  (:require [necessary-evil.core :as xml-rpc]))


(defn get-system-state
  "Returns the result of xmlrpc call to getSystemState."
  []
  (xml-rpc/call "http://localhost:11311/RPC2" :getSystemState "/script"))

(defn get-topics
  "Returns the ROS topics"
  []
  (get (get-system-state) 2))


(defn -main []
  (println (get-topics)))
