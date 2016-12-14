git remote add -f remoteProject https://github.com/VerkhovtsovPavel/$1
git merge -s ours --no-commit remoteProject/master
git read-tree --prefix=$1/ -u remoteProject/master
git add --all
git commit -am "Add $1"
git pull -s subtree remoteProject master
git remote remove remoteProject