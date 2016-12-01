(ns chat.login-test
  (:require [chat.data.persistance :as pers]
            [monger.core :as mcore])

  (defn use-db [f]
        (binding [pers/db (mcore/get-db pers/conn "chat-test")]
          (f)
          (mcore/drop-db pers/conn pers/db))))