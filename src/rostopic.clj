(ns rostopic
  (:require [necessary-evil.core :as xml-rpc]))


(defn get-master-uri
  []
  (or (System/getenv "ROS_MASTER_URI")
       "http://localhost:11311"))


(defn get-system-state
  "Returns the result of xmlrpc call to getSystemState."
  [caller-id]
  (xml-rpc/call (str (get-master-uri) "/RPC2") :getSystemState caller-id))

(defn get-topics-info
  "Returns the ROS topics"
  []
  (let [system-state-response (get-system-state "/script")
        system-state (last system-state-response)
        topics-info (take 2 system-state)]
    topics-info))

; Probably better functional ways to do this
(defn print-topics
  "Prints the ROS topics"
  []
  (let [topics-info (get-topics-info)
        published-topics (first topics-info)
        subscribed-topics (last topics-info)]
    (doseq [item (map first published-topics)]
      (println item))
    (doseq [item (map first subscribed-topics)]
      (println item))))

(defn -main []
  (print-topics))
