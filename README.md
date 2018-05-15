# backend_teste2

Instruções de uso e funcionamento da aplicação:

Para converter um vídeo acesse o menu 'Upload Vídeo', selecione um arquivo de vídeo como o 'sample.dv' e defina um nome desejado para ele.

Ao clicar no botão 'Converter' o vídeo será armazenado em um bucket na Amazon S3 e posteriormente será convertido para um formato compativel com os padrões da Web (.mp4).

Para realizar a conversão foi utilizado o serviço de enconding Zencoder.  Assim que um arquivo é inserido no folder /inputs o serviço AWS Lambda aciona o Zencoder que realiza a conversão do vídeo e armazena o resultado no folder /outputs.

OBS: O armazenamento do vídeo no bucket da Amazon pode demorar até 20 min, se o arquivo for muito grande, e a conversão no Zencoder até 2 min.

OBS2: Não foi realizado teste unitário, nem a validação dos campos de entrada por falta de tempo. Desculpe! 
