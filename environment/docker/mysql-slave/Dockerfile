# 使うDockerイメージ
FROM mysql:5.7

# MySQL設定ファイルをイメージ内にコピー
ADD ./my.cnf /etc/mysql/conf.d/my.cnf

# テーブル初期化SQLをコピー
ADD ./init/start-slave.sh /docker-entrypoint-initdb.d/start-slave.sh

# docker run時に実行されるコマンド
CMD ["mysqld"]