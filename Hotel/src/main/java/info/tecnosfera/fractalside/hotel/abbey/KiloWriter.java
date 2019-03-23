/*
-------------------------------------------------------------------------
fractalside's Hotel - Alpha 0.0.2
(Don't use yet. There's work left)
-------------------------------------------------------------------------
http://fractalside.tecnosfera.info , https://github.com/fractalside
"The miracle is this: the more we share the more we have" 
                                           Leonard Nimoy 1931 - 2015
-------------------------------------------------------------------------
Copyright 2018 fractalside (Gonzalo Virgos Revilla)
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package info.tecnosfera.fractalside.hotel.abbey;

public class KiloWriter extends JsonWriter {

//		"bulletinList":{
//			"handle":".gp4-anuncios",
//			"classes":{"ok":"kt-ok","review":"kt-review","warn":"kt-warn","reload":"kt-reload"}},
//			/*new0.6{*/"submitted":{"note": "Formulario enviado", "msDelayIn": 2000},/*}*/		
//			/*new0.6{*/"focusable":["reload"],/*}*/
//			/*mod0.8{*/"popupable":["review:3"]/*}*/
//		},
//		/*new0.7{*/
//		'keyboard':{'ctrlAltConsoleJqh':'#gp4-consola'},
//		"field":{"popupCssc":"gp4-pop","firstJqh":".gp4-primero"},
//		/*}*/
//		/*old 0.6 "firstField":{"handle":".gp4-primero"},*/
//		"fieldNotes":{
//			"prefix":"nota_campo_",
//			"borderInk":{"normal":"#c0c0c0","warn":"#ffa000","review":"#0000c0"}
//		},
//		"jumpSelect":{"handle":".gp4-cascada","submitValue":"cascada","remove":", y pulse aceptar"},
//		"freeSelect":{"handle":".gp4-libre","opt":"*libre","suffix":"_libre","width" : 350},
//	});
//	
//});
	
	public String getInitialization() {
		putBoolean("submitLock", false); //new 0.11
		openItem("bulletinList");
		assign("handle",		".gp4-anuncios");
		putItem("classes", 		"ok","kt-ok","review","kt-review","warn","kt-warn","reload","kt-reload");
		close(1);
		putItem("keyboard",		"ctrlAltConsoleJqh","#gp4-consola");
		putItem("field",		"popupCssc","gp4-pop","firstJqh",".gp4-primero");
		putItem("menuLinks", 	"except",".gp4-corelink");
		putItem("submitCheck", 	"disabled","gp4-gris","except","gp4-descarga");
		putItem("table",		"even","gp4-par","odd","gp4-impar","hoverSuffix","-resalta");
		putItem("nCheckTable",	"handle",".gp4-multiselector","tr","&lt;tr>&lt;td>$k&lt;/td>&lt;td>Todos/ninguno&lt;/td>&lt;/tr>");
		putItem("checkPass",	"capsLockText","Warning! CapsLock enabled");
		return dump();
	}
	
}
