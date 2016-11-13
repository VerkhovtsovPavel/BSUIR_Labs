goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.object', 'goog.math.Integer', 'goog.string.StringBuffer', 'goog.array', 'goog.math.Long']);
goog.addDependency("../cljs/chat/client/html_utils.js", ['cljs.chat.client.html_utils'], ['cljs.core']);
goog.addDependency("../clojure/string.js", ['clojure.string'], ['goog.string', 'cljs.core', 'goog.string.StringBuffer']);
goog.addDependency("../cljs/chat/client/core.js", ['cljs.chat.client.core'], ['cljs.chat.client.html_utils', 'cljs.core', 'clojure.string']);
goog.addDependency("../cljs/chat/client/uiHandlers.js", ['cljs.chat.client.uiHandlers'], ['cljs.chat.client.html_utils', 'cljs.core', 'cljs.chat.client.core']);
